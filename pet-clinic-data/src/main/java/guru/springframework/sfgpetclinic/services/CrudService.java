package guru.springframework.sfgpetclinic.services;

import java.util.Set;

/**
 * @author matt.mcfarlane
 *
 * @param <T>  --  Type
 * @param <ID> --  ID
 * 
 * Section 4; Lecture 61:  Initially, we'll be implementing a Map model, but will eventually implement a JPA model
 *		Introduced 'CrudService', by copying the pattern from  -->    org.springframework.data.repository.CrudRepository.class  (can open under "Maven Dependencies")
 * 		John set this up based somewhat on the CrudRepository interface, but will have some differences, because we are working with Services rather than Repositories.
 * 		We're utilising Java Generics.
 */
public interface CrudService<T, ID> {

	Set<T> findAll();
	
	T findById(ID id);
	
	T save(T object);
	
	void delete(T object);
	
	void deleteById(ID id);
}
