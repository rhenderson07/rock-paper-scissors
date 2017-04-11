package app.model;

import java.security.SecureRandom;

public class ComputerPlayer extends Player {
	
	private static final SecureRandom RANDOM = new SecureRandom();
	
	public ComputerPlayer() {
		super.setAction(getRandomAction());
	}
	
	private PlayerAction getRandomAction(){
		int actionIndex = RANDOM.nextInt(PlayerAction.values().length);
		return PlayerAction.values()[actionIndex];
	}
}
