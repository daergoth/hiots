package net.daergoth.serviceapi.monitor;

import java.util.List;

/**
 * Defines all methods, that a OverviewLayout-provider service-bean must have.
 * Used for getting {@code OverviewLayout}s from an {@code OverviewLayoutDAO}.
 * For it's default implementation check the Service layer.
 */
public interface OverviewLayoutContainerLocal {
	
	/**
	 * Retrieves all {@code OverviewLayout}s from the database.
	 * 
	 * @return a list of layouts
	 */
	public List<OverviewLayoutVO> getLayout();
	
	/**
	 * Adds an {@code OverviewLayout} to the database.
	 * 
	 * @param layout  the layout to persist
	 */
	public void addLayout(OverviewLayoutVO layout);
	
	/**
	 * Updates an {@code OverviewLayout} in the database.
	 * 
	 * @param layout  the layout to update
	 */
	public void updateLayout(OverviewLayoutVO layout);
	
	/**
	 * Deletes an {@code OverviewLayout} from the database.
	 * 
	 * @param id  the ID of the layout to delete
	 */
	public void deleteLayout(long id);
	
}
