package net.daergoth.serviceapi.monitor;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a layout setting for the Overview page.
 * Every {@code OverviewLayout} has a list of {@code OverviewLayoutElement}s,
 * which are for saving various informational widget's configuration.
 * 
 * @see net.daergoth.serviceapi.monitor.OverviewLayoutElementVO
 */
public class OverviewLayoutVO implements Serializable {

	private static final long serialVersionUID = -666391228690399646L;

	private Long id;

	private String name;

	private List<OverviewLayoutElementVO> elements;

	/**
	 * Getter for the {@code OverviewLayout}'s ID.
	 * @return the ID of the layout
	 */
	public Long getId() {
		return id;
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
		return name;
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
	 * @return the list of layout elements
	 */
	public List<OverviewLayoutElementVO> getElements() {
		return elements;
	}

	/**
	 * Setter for the {@code OverviewLayout}'s {@code OverviewLayoutElement} list.
	 * @param elements  the list of new elements for the layout
	 */
	public void setElements(List<OverviewLayoutElementVO> elements) {
		this.elements = elements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OverviewLayoutVO other = (OverviewLayoutVO) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
	
}
