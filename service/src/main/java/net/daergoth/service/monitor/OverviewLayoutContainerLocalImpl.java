package net.daergoth.service.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import net.daergoth.coreapi.monitor.OverviewLayoutDaoLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.monitor.OverviewLayoutContainerLocal;
import net.daergoth.serviceapi.monitor.OverviewLayoutVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

/**
 * Default implementation for {@code OverviewLayoutContainerLocal}.
 */
@Stateful
@Local(OverviewLayoutContainerLocal.class)
public class OverviewLayoutContainerLocalImpl implements OverviewLayoutContainerLocal {
	
	@EJB
	private OverviewLayoutDaoLocal layoutDao;
	
	private boolean changed = true;
	
	private List<OverviewLayoutVO> layouts;
	
	@PostConstruct
	private void init() {
		layouts = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OverviewLayoutVO> getLayouts() {
		if (changed) {
			try {
				layouts = OverviewLayoutConverter.toVOs(layoutDao.getLayouts());
			} catch (ActorConvertException | SensorConvertException e) {
				e.printStackTrace();
				System.out.println("BAJ VAN AZ OVERVIEW CONTAINERBEN");
			}
		}
		
		return layouts;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLayout(OverviewLayoutVO layout) {
		changed = true;
		layoutDao.addLayout(OverviewLayoutConverter.toDTO(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLayout(OverviewLayoutVO layout) {
		changed = true;
		layoutDao.updateLayout(OverviewLayoutConverter.toDTO(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteLayout(long id) {
		changed = true;
		layoutDao.deleteLayout(id);
	}

}
