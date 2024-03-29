package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;

/**
 * We're providing an alternate implementation of the OwnerService interface (Map and JPA)
 * 
 * @author matt.mcfarlane
 *
 */

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
	
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;

	public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository, 
							 PetTypeRepository petTypeRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}
	
	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners::add);  // retrieve ALL 'Owner' entities from the database, and store each in our 'owners' Set, to be passed back to the calling method
		return owners;
	}

	@Override
	public Owner findById(Long id) {
/*
		Optional<Owner> optionalOwner = ownerRepository.findById(id);

//		if(optionalOwner.isPresent()) {          // IntelliJ actually reported that this statement could be written in a more functional-style
//			return optionalOwner.get();
//		} else {
//			return null;
//		}
		
		return optionalOwner.orElse(null);
*/
		return ownerRepository.findById(id).orElse(null);   // refactored once more, and ended up with a single line of code!!
}

	@Override
	public Owner save(Owner object) {
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		ownerRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		ownerRepository.deleteById(id);		
	}
}
