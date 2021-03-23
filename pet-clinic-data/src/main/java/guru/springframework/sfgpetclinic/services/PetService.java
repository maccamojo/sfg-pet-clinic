package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Pet;

public interface PetService extends CrudService<Pet, Long> {
	
	//Pet findById(Long id);   // now declared in my CrudService interface
	
	//Pet save(Pet pet);
	
	//Set<Pet> findAll();

}
