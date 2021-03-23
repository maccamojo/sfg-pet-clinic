package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects needing this property.
 * 
 * As the project evolves, we will add JPA annotations to this class.
 *
 * @author Matt McFarlane
 */
public class BaseEntity implements Serializable {
	
	private Long id;  // Hibernate recommendation is to use 'box' types, rather than primitives. Box types can have the value 'null', but primitives cannot.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
