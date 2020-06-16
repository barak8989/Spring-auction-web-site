// File: Category.java
// Category class represents category of auction. Contains constructor, getters and setters.
package spring.model;

public class Category {
		
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category( Long id, String name) {
		this.id = id;
		this.name = name;
	}

	
}
