package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

/**
 *  * @author matt.mcfarlane
 *
 *	Section 4; Lecture 61:  Initially, we'll be implementing a Map model, but will eventually implement a JPA model
 *		Introduced 'CrudService', by copying the pattern from  -->    org.springframework.data.repository.CrudRepository.class  (can open under "Maven Dependencies")
 *
 */
public interface OwnerService extends CrudService<Owner, Long> {

	Owner findByLastName(String lastName);
	
	//Owner findById(Long id);   // now declared in my CrudService interface
	
	//Owner save(Owner owner);
	
	//Set<Owner> findAll();
	
}
