package net.daergoth.coreapi.monitor;

import java.util.List;

/**
 * Provides access for the database for obtaining {@code OverviewLayout} objects.
 *
 * @see net.daergoth.coreapi.monitor.OverviewLayoutDTO
 */
public interface OverviewLayoutDaoLocal {
	
	/**
	 * Obtains all {@code OverviewLayout}s from the database.
	 * @return the list of layouts
	 */
	public List<OverviewLayoutDTO> getLayouts();
	
	/**
	 * Persists an {@code OverviewLayout} by writing it into the database.
	 * @param rule the rule to persist
	 */
	public void addLayout(OverviewLayoutDTO layout);
	
	/**
	 * Updates a {@code OverviewLayout}'s data in the database.
	 * @param layout the layout to update
	 */
	public void updateLayout(OverviewLayoutDTO layout);
	
	/**
	 * Deletes the {@code OverviewLayout} with specified ID from the database.
	 * @param id the ID of the layout that will be deleted
	 */
	public void deleteLayout(Long id); 
	
}
