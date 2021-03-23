package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {

	/**
	 * In the Spring version of the PetClinic app, the OwnerController directly uses the OwnerRepository.
	 * John prefers to have a Service-layer, and have that service-layer interact with the Repository.
	 * Allows us to wire-in different implementations (eg. HashMap, JPA Repositories, etc)
	 */
	private final OwnerService ownerService;
	
	public OwnerController(OwnerService ownerService) {  // Constructor Injection is the best practice when working with injected properties
		this.ownerService = ownerService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listOwners(Model model) {
		
		model.addAttribute("owners", ownerService.findAll());  // provides a 'Set' of Owners to our Model that our View will be able to iterate over
		return "owners/index";
	}
	
	@RequestMapping("/find")
	public String findOwners() {
		return "notimplemented";
	}
}
