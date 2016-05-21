package net.daergoth.service.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.daergoth.coreapi.monitor.OverviewLayoutDaoLocal;
import net.daergoth.service.cobertura.CoverageIgnore;
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

	private static final Logger logger = LoggerFactory.getLogger(OverviewLayoutContainerLocal.class);
	
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
	@CoverageIgnore
	@Override
	public List<OverviewLayoutVO> getLayouts() {
		if (changed) {
			try {
				layouts = OverviewLayoutConverter.toVOs(layoutDao.getLayouts());
			} catch (ActorConvertException | SensorConvertException e) {
				logger.error("Error during OverviewLayoutElement's Action/Sensor converting.", e);
			}
		}
		
		return layouts;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void addLayout(OverviewLayoutVO layout) {
		changed = true;
		layoutDao.addLayout(OverviewLayoutConverter.toDTO(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void updateLayout(OverviewLayoutVO layout) {
		changed = true;
		layoutDao.updateLayout(OverviewLayoutConverter.toDTO(layout));
	}

	/**
	 * {@inheritDoc}
	 */
	@CoverageIgnore
	@Override
	public void deleteLayout(long id) {
		changed = true;
		layoutDao.deleteLayout(id);
	}

}
