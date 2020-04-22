package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
//import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
//import guru.springframework.sfgpetclinic.services.map.VetServiceMap;

/**
 * @author matt.mcfarlane
 * This is a Spring Bean that is configured to 'run' when it is instantiated.
 * It is possible, I think, to have several of these and set the order they 'run' by using the @Order annotation.
 * This class will be executed once the Spring Context is up & running.
 */

@Component
public class DataLoader implements CommandLineRunner {
	
	// we're implementing these 2 services.
	// in the future, we'll leverage Spring to do this config for us
	private final OwnerService ownerService;
	private final VetService vetService;	

	public DataLoader(OwnerService ownerService, VetService vetService) {   // Constructor Injection
		this.ownerService = ownerService;
		this.vetService = vetService;
	}

/*	//No longer required, as we're now doing Constructor Injection
	public DataLoader() {
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}
*/
	
	@Override
	public void run(String... args) throws Exception {
		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		
		ownerService.save(owner2);
		
		System.out.println("Loaded Owners....");
		
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets....");
	}
}
