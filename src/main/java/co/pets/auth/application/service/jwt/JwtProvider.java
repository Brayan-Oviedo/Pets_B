package co.pets.auth.application.service.jwt;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import co.pets.auth.application.domain.jwt.JwtDto;
import co.pets.auth.application.domain.user.UserAuth;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	
	public String generateToken(Authentication authentication) {
		UserAuth userAuth = (UserAuth) authentication.getPrincipal();
		List<String> rols = userAuth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return Jwts.builder()
				.setSubject(userAuth.getUsername())
				.claim("rols", rols)
				.setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
	}

	@SuppressWarnings("unchecked")
	public String refreshToken(JwtDto jwtDto) throws ParseException {
		JWT jwt = JWTParser.parse(jwtDto.getToken());
		JWTClaimsSet claims = jwt.getJWTClaimsSet();
		String userName = claims.getSubject();
		List<String> rols = (List<String>) claims.getClaim("rols");
		return Jwts.builder()
				.setSubject(userName)
				.claim("rols", rols)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact(); 
	}
	
	public String getUserNameFromToken(String token) {
		return Jwts
				.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token).getBody()
				.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			if(e instanceof MalformedJwtException) {
				
			}else if( e instanceof ExpiredJwtException) {
			
			}else { }
		}
		return false;
	}

}
