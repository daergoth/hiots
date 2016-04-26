package net.daergoth.core.rule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.daergoth.core.actor.Actor;
import net.daergoth.core.actor.ActorState;

@Entity
@Table(name = "actions")
public class Action implements Serializable {
	   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8294785838870585395L;
	
	@Id
	private Long id;
	
	@Column(nullable = false)
	private Long actorId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "actorId", referencedColumnName = "id")
	private Actor actor;
	
	@Embedded
	private ActorState value;

	public Action() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public ActorState getValue() {
		return value;
	}

	public void setValue(ActorState value) {
		this.value = value;
	}   
   
}
