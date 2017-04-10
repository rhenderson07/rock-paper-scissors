package app.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
	private int playerId;
	private PlayerAction action;
	private PlayerStatus status;

	public Player(int playerId) {
		this.playerId = playerId;
		this.status = PlayerStatus.AWAITING_ACTION;
	}

	public int getPlayerId() {
		return playerId;
	}

	public List<PlayerAction> getAvailableActions() {
		if (status == PlayerStatus.AWAITING_ACTION) {
			return Arrays.asList(PlayerAction.values());
		}
		return Collections.emptyList();
	}

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
