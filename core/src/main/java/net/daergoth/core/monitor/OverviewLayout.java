package net.daergoth.core.monitor;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * JPA Entity representing an Overview layout saved by the user.
 * The layout is built up by many {@code OverviewLayoutElement}s.
 *
 * @see net.daergoth.core.monitor.OverviewLayoutElement
 */
@Entity
@Table(name = "overview_layouts")
@NamedQueries({
	@NamedQuery(name = "OverviewLayout.findAll", query = "SELECT l FROM OverviewLayout l")
})
public class OverviewLayout implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OverviewLayoutElement> elements;

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
	public List<OverviewLayoutElement> getElements() {
		return elements;
	}

	/**
	 * Setter for the {@code OverviewLayout}'s {@code OverviewLayoutElement} list.
	 * @param elements  a list of the new element for the layout
	 */
	public void setElements(List<OverviewLayoutElement> elements) {
		this.elements = elements;
	}
	
}
