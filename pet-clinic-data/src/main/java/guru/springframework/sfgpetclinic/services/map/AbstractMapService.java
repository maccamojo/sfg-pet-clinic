package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {  // anytime we extend out this class as long as a Long (or anything that extends a Long) is specified

	//protected Map<ID, T> map = new HashMap<>();
	protected Map<Long, T> map = new HashMap<>();
	
	Set<T> findAll() {
		return new HashSet<>(map.values());
	}
	
	T findById(ID id) {
		return map.get(id);
	}
	
	T save(T object) {
		
		if (object != null) {
			if (object.getId() == null) {
				object.setId(getNextId());
			}
			
			map.put(object.getId(), object);
		} else {
			throw new RuntimeException("Object cannot be null");
		}
		
		return object;
	}
	
	void deleteById(ID id) {
		map.remove(id);
	}
	
	void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));  // Lambda
	}
	
	private Long getNextId() {
		
/**
 * 		John's refactored code
 
		Long nextId = null;
		
		try {
			nextId = Collections.max(map.keySet()) + 1;
		} catch (NoSuchElementException e) {
			nextId = 1L;
		}
		return nextId;
*/
		
		// better refactored code -- Comment..  "Good suggestion. Defensive coding is always better then try/catch"
/**
 		if (map.isEmpty())
	        return 1L;
	    else
	        return Collections.max(map.keySet()) + 1;
*/	
		// and another refactor
		return map.size() > 0 ? Collections.max(map.keySet()) + 1 : 1;
		
	}
}
