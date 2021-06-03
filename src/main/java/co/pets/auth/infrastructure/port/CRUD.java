package co.pets.auth.infrastructure.port;

import java.util.List;

public interface CRUD<T, T2> {

	T findById(T2 id) throws Exception;
	void save (T t) throws Exception;
	void update(T t)throws Exception;
	void deleteById(T2 id);
	List<T> getAll();
		
}
