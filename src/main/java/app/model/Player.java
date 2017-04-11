package app.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player implements Identifiable<Integer>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer playerId;
	private PlayerAction action;
	private PlayerStatus status;
	
	@ManyToOne
	private Game game;

//	public Player(int playerId) {
//		this.playerId = playerId;
//		this.status = PlayerStatus.AWAITING_ACTION;
//	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return playerId;
	}

//	public int getPlayerId() {
//		return playerId;
//	}

	public PlayerAction getAction() {
		if (status == PlayerStatus.REVEALED) {
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
}
