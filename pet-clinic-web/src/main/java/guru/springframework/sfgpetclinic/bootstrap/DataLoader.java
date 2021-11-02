package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
//import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
//import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import guru.springframework.sfgpetclinic.services.VisitService;

/**
 * @author matt.mcfarlane
 * This is a Spring Bean that is configured to 'run' when it is instantiated.
 * It is possible, I think, to have several of these and set the order they 'run' by using the @Order annotation.
 * This class will be executed once the Spring Context is up & running.
 */

@Component
public class DataLoader implements CommandLineRunner {
	
	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;

	/**
	 * Spring is injecting my "Map" implementations here, as it's the only implementation available in the Application Context.
	 * 
	 * In the future, when we have "JPA" implementations, we'll utilise Spring Profiles to select the appropriate implementation.
	 * 
	 * @param ownerService
	 * @param vetService
	 * @param petTypeService
	 * @param specialtyService
	 */
	@Autowired   // now longer required to explicitly use this annotation
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {   // Constructor Injection
		this.specialtyService = specialtyService;
		this.petTypeService = petTypeService;
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.visitService = visitService;
	}

/*	//No longer required, as we're now doing Constructor Injection
	public DataLoader() {
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}
*/
	
	@Override
	public void run(String... args) throws Exception {
		
		int count = petTypeService.findAll().size();
		
		if (count == 0) {   // in the future, when we're persisting to a database, we don't want to reload data each time the app starts up
			loadData();
		}
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		Speciality savedRadiology = specialtyService.save(radiology); // we're persisting to our Map in order to get an Id value
				
		Speciality surgery = new Speciality();
		surgery.setDescription("Surgery");
		Speciality savedSurgery = specialtyService.save(surgery);
		
		Speciality dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		Speciality savedDentistry = specialtyService.save(dentistry);
		
		Owner owner1 = new Owner();
		//owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("1231231234");
		
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		owner1.getPets().add(mikesPet);
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		//owner2.setId(2L);
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");
		owner2.setAddress("123 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("1231231234");
		
		Pet fionasCat = new Pet();
		fionasCat.setPetType(savedCatPetType);
		fionasCat.setOwner(owner2);
		fionasCat.setBirthDate(LocalDate.now());
		fionasCat.setName("Pussy");
		owner2.getPets().add(fionasCat);
		
		ownerService.save(owner2);
		
		Visit catVisit = new Visit();
		catVisit.setPet(fionasCat);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Sneezy Kitty");
		
		visitService.save(catVisit);
		
		System.out.println("Loaded Owners....");
		
		Vet vet1 = new Vet();
		//vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		vet1.getSpecialities().add(savedRadiology);
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		//vet2.setId(2L);
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		vet1.getSpecialities().add(savedSurgery);
		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets....");
	}
}
