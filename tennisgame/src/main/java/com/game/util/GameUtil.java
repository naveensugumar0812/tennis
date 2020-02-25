package com.game.util;

import com.game.model.Player;

public class GameUtil {

	public static Player setPlayerInfo(int playerScore, String playerName){

		Player player = new Player();
		player.setPlayerName(playerName);
		player.setPlayerScore(playerScore);
		return player;
	}

	public static boolean isValidScore(int pOneScore, int pTwoScore) {

		boolean validScore= true;

		if(pOneScore < 0 || pTwoScore < 0 ) {
			validScore=false;
		} else if(pOneScore>=3 && pTwoScore>=3) {
			validScore= Math.abs(pOneScore - pTwoScore)<=2;
		}

		return validScore;

	}



	public static boolean checkForDeuce(Player firstPlayer, Player secondPlayer) {
		boolean deuce= firstPlayer.getPlayerScore() >= 3 && secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore();
		return deuce;
	}

	public static String getLeadingScorer(Player firstPlayer, Player secondPlayer) {
		if (firstPlayer.getPlayerScore() > secondPlayer.getPlayerScore()) {
			return firstPlayer.getPlayerName();
		} else {
			return secondPlayer.getPlayerName();
		}
	}

	public static boolean checkForWinner(Player firstPlayer, Player secondPlayer) {
		if((secondPlayer.getPlayerScore() >= 3 && secondPlayer.getPlayerScore() >= firstPlayer.getPlayerScore() + 2 ) 
				|| (firstPlayer.getPlayerScore() >= 3 && firstPlayer.getPlayerScore() >= secondPlayer.getPlayerScore() + 2)){
			return true;
		}
		return false;
	}

	public static boolean checkForAdvantage(Player firstPlayer, Player secondPlayer) {

		if ((firstPlayer.getPlayerScore() >= 3 && firstPlayer.getPlayerScore() == secondPlayer.getPlayerScore() + 1) 
				|| (secondPlayer.getPlayerScore() >= 3 && secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore() + 1)){
			return true;
		}

		return false;

	}
	
	public static void addScore(Player player) {
		player.setPlayerScore(player.getPlayerScore()+1);
	}

	public static String getScore(int score) {
		switch (score) {
		case 2:
			return GameConstant.SCORE_THIRTY;
		case 1: 
			return GameConstant.SCORE_FIFTEEN;
		default:
			return GameConstant.SCORE_LOVE;
		}
	}


}
