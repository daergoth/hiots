package net.daergoth.serviceapi.actors.dummy;

import net.daergoth.serviceapi.actors.ActorVO;

public abstract class DummyActorVO extends ActorVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443695062688668343L;
	
	public DummyActorVO() {
		super();
	}

	public DummyActorVO(long id, String name) {
		super(id, name);
	}

}
