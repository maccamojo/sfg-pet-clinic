package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects needing this property.
 * 
 * As the project evolves, we will add JPA annotations to this class.
 *
 * @author Matt McFarlane
 */
@MappedSuperclass    // telling JPA that we don't need this specific class mapped to the database
public class BaseEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // Hibernate recommendation is to use 'box' types, rather than primitives. Box types can have the value 'null', but primitives cannot.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
