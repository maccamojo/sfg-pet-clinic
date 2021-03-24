package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vets")
public class Vet extends Person {

	@ManyToMany(fetch = FetchType.EAGER)     // JPA is going to try to load the Specialties upfront (all at once)
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"), 
		inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Speciality> specialities = new HashSet<>();   // initialise here to avoid a NullPointerException in the DataLoader (Best Practice)

	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}
	
}
