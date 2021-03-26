package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

	/**
	 * This will be using those dynamic query methods of Spring Data JPA.
	 * Spring Data JPA will set up a method implementation for us   <--  investigate how this works!!
	 * 
	 * @param lastName
	 * @return
	 */
	Owner findByLastName(String lastName);
}
