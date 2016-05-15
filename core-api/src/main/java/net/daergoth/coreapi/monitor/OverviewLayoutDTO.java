package net.daergoth.coreapi.monitor;

import java.util.List;

/**
 * Represents an {@code OverviewLayout} in the Core API.
 * This class has a list of {@code OverviewLayoutElementDTO}s,
 * which are for building up the layout itself.
 * 
 * @see net.daergoth.coreapi.monitor.OverviewLayoutElementDTO
 */
public class OverviewLayoutDTO {

	private Long id;

	private String name;

	private List<OverviewLayoutElementDTO> elements;

	/**
	 * Getter for the {@code OverviewLayout}'s ID.
	 * @return the ID of the layout
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Setter for the {@code OverviewLayout}'s ID.
	 * @param id  the new ID for the layout
	 */
	public void setId(Long id) {
		this.id = id;
	}   
	
	/**
	 * Getter for the {@code OverviewLayout}'s name.
	 * @return the name of the layout
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for the {@code OverviewLayout}'s name.
	 * @param name  the new name for the layout
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the {@code OverviewLayout}'s {@code OverviewLayoutElement} list.
	 * @return the list of the elements of the layout
	 */
	public List<OverviewLayoutElementDTO> getElements() {
		return elements;
	}

	/**
	 * Setter for the {@code OverviewLayout}'s {@code OverviewLayoutElement} list.
	 * @param elements  a list of the new element for the layout
	 */
	public void setElements(List<OverviewLayoutElementDTO> elements) {
		this.elements = elements;
	}
}
