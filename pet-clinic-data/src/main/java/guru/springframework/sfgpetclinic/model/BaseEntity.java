package guru.springframework.sfgpetclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	
	private Long id;  // Hibernate recommendation is to use 'box' types, rather than primitives. Box types can have the value 'null', but primitives cannot.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
