package net.daergoth.serviceapi.monitor;

import java.io.Serializable;
import java.util.List;

public class OverviewLayoutVO implements Serializable {

	private static final long serialVersionUID = -666391228690399646L;

	private Long id;

	private String name;

	private List<OverviewLayoutElementVO> elements;

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

	public List<OverviewLayoutElementVO> getElements() {
		return elements;
	}

	public void setElements(List<OverviewLayoutElementVO> elements) {
		this.elements = elements;
	}
	
	
	
}
