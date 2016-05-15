package net.daergoth.service.monitor;

import java.util.List;

import javax.ejb.EJB;

import net.daergoth.coreapi.monitor.OverviewLayoutDaoLocal;
import net.daergoth.serviceapi.actors.ActorConvertException;
import net.daergoth.serviceapi.monitor.OverviewLayoutContainerLocal;
import net.daergoth.serviceapi.monitor.OverviewLayoutVO;
import net.daergoth.serviceapi.sensors.SensorConvertException;

public class OverviewLayoutContainerLocalImpl implements OverviewLayoutContainerLocal {
	
	@EJB
	private OverviewLayoutDaoLocal layoutDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OverviewLayoutVO> getLayout() {
		try {
			return OverviewLayoutConverter.toVOs(layoutDao.getLayouts());
		} catch (ActorConvertException | SensorConvertException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLayout(OverviewLayoutVO layout) {
		layoutDao.addLayout(OverviewLayoutConverter.toDTO(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLayout(OverviewLayoutVO layout) {
		layoutDao.updateLayout(OverviewLayoutConverter.toDTO(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteLayout(long id) {
		layoutDao.deleteLayout(id);
	}

}
