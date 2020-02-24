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



	public static boolean isDeuce(Player firstPlayer, Player secondPlayer) {
		return firstPlayer.getPlayerScore() >= 3 
				&& secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore();
	}

	public static String playerWithHighestScore(Player firstPlayer, Player secondPlayer) {
		if (firstPlayer.getPlayerScore() > secondPlayer.getPlayerScore()) {
			return firstPlayer.getPlayerName();
		} else {
			return secondPlayer.getPlayerName();
		}
	}

	public static boolean hasWinner(Player firstPlayer, Player secondPlayer) {
		if(secondPlayer.getPlayerScore() >= 4 
				&& secondPlayer.getPlayerScore() >= firstPlayer.getPlayerScore() + 2 )
			return true;
		if(firstPlayer.getPlayerScore() >= 4 
				&& firstPlayer.getPlayerScore() >= secondPlayer.getPlayerScore() + 2)
			return true;
		return false;
	}

	public static boolean hasAdvantage(Player firstPlayer, Player secondPlayer) {

		if (firstPlayer.getPlayerScore() >= 4 
				&& firstPlayer.getPlayerScore() == secondPlayer.getPlayerScore() + 1)
			return true;

		if (secondPlayer.getPlayerScore() >= 4 
				&& secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore() + 1)
			return true;


		return false;

	}

	/*
	 * public static void playerOneScores(Player firstPlayer) {
	 * firstPlayer.setPlayerScore(firstPlayer.getPlayerScore()+1); }
	 * 
	 * public static void playerTwoScores(Player secondPlayer) {
	 * secondPlayer.setPlayerScore(secondPlayer.getPlayerScore()+1); }
	 */

	public static String getScore(int score) {
		switch (score) {
		case 3:
			return GameConstant.SCORE_FORTY;
		case 2:
			return GameConstant.SCORE_THIRTY;
		case 1: 
			return GameConstant.SCORE_FIFTEEN;
		case 0:
			return GameConstant.SCORE_LOVE;
		}
		throw new IllegalArgumentException(GameConstant.SCORE_EXCEPTION + score);
	}


}
