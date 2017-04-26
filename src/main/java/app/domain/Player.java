package app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player implements Identifiable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer playerId;
	
	private PlayerAction action;
	private PlayerStatus status;

	@ManyToOne
	private Game game;

	public Player() {
		this.status = PlayerStatus.AWAITING_ACTION;
	}

	@JsonIgnore
	@Override
	public Integer getId() {
		return playerId;
	}

	public PlayerAction getAction() {
		if (isRevealed()) {
			return action;
		}
		return null;
	}

	public void setAction(PlayerAction action) {
		this.action = action;
		this.status = PlayerStatus.ACTION_SELECTED;
	}

	public PlayerStatus getStatus() {
		return status;
	}

	public void reveal() {
		this.status = PlayerStatus.REVEALED;
	}

	@JsonIgnore
	public boolean isRevealed() {
		return this.status == PlayerStatus.REVEALED;
	}
}
