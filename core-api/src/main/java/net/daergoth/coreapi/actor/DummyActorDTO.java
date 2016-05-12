package net.daergoth.coreapi.actor;

/**
 * Represents {@code Actor}s, which are virtual devices.
 * These objects are needed to isolate the simulated {@code Actor}s from real-life ones.
 * {@code DummyActorDTO}s have no differences in comparison to {@code ActorDTO}s
 * in the Core layer, but in the Service layer there are differences.
 */
public class DummyActorDTO extends ActorDTO {

	private static final long serialVersionUID = -9121730266813026802L;

}
