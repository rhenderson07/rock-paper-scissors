package app.model;

public class Player {
	private int playerId;
	private PlayerAction action;
	private PlayerStatus status;

	public Player(int playerId) {
		this.playerId = playerId;
		this.status = PlayerStatus.AWAITING_ACTION;
		this.action = PlayerAction.UNKNOWN;
	}

	public int getPlayerId() {
		return playerId;
	}

	public PlayerAction getAction() {
		if (status == PlayerStatus.REVEALED) {
			return action;
		}
		return PlayerAction.UNKNOWN;
	}

	public void setAction(PlayerAction action) {
		this.action = action;
		this.status = PlayerStatus.ACTION_SELECTED;
	}

	public PlayerStatus getStatus() {
		return status;
	}
	
	public void reveal(){
		this.status = PlayerStatus.REVEALED;
	}
}
