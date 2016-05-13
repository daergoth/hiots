package net.daergoth.core.rule;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.daergoth.core.actor.Actor;
import net.daergoth.core.actor.ActorState;

/**
 * JPA Entity class for {@code Action}s.
 * Each instance of this class have a parent {@code Rule} entity, as well as
 * a {@code Actor} and {@code ActorState} entity, both of them used for 
 * executing the {@code Action} itself.
 * 
 * @see net.daergoth.core.rule.Rule
 * @see net.daergoth.core.actor.Actor
 * @see net.daergoth.core.actor.ActorState
 */
@Entity
@Table(name = "actions")
public class Action implements Serializable {

	private static final long serialVersionUID = -8294785838870585395L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "actorId", referencedColumnName = "id")
	private Actor actor;
	
	@Embedded
	private ActorState value;

	/**
	 * Getter for the {@code Action}'s ID.
	 * @return the ID of the action
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the {@code Action}'s ID.
	 * @param id the new ID of the action
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for the {@code Action}'s {@code Actor}, on which the action will be executed.
	 * @return the actor of the action
	 */
	public Actor getActor() {
		return actor;
	}

	/**
	 * Setter for the {@code Action}'s {@code Actor}, on which the action will be executed.
	 * @param actor the new actor for the action
	 */
	public void setActor(Actor actor) {
		this.actor = actor;
	}

	/**
	 * Getter for the {@code Action}'s {@code ActorState} which is used setting the actor to it.
	 * @return the value of the action
	 */
	public ActorState getValue() {
		return value;
	}

	/**
	 * Setter for the {@code Action}'s {@code ActorState} which is used setting the actor to it.
	 * @param value the new value of the action
	 */
	public void setValue(ActorState value) {
		this.value = value;
	}   
   
}
