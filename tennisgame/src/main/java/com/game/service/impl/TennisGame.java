package com.game.service.impl;

import org.springframework.stereotype.Repository;

import com.game.model.Player;
import com.game.service.GameStrategy;
import com.game.util.GameConstant;
import com.game.util.GameUtil;

@Repository
public class TennisGame implements GameStrategy {

	private Player firstPlayer;
	private Player secondPlayer;

	public String generateScore(String firstScore, String secondScore) {

		try {
			int playerOneScore=Integer.parseInt(firstScore);
			int playerTwoScore=Integer.parseInt(secondScore);

			firstPlayer= GameUtil.setPlayerInfo(playerOneScore, GameConstant.PLAYER_ONE_NAME);
			secondPlayer=GameUtil.setPlayerInfo(playerTwoScore, GameConstant.PLAYER_TWO_NAME);

			if(GameUtil.isValidScore(playerOneScore, playerTwoScore)){

				if (GameUtil.isDeuce(firstPlayer,secondPlayer)) {
					return GameConstant.DEUCE;
				}

				if (GameUtil.hasAdvantage(firstPlayer,secondPlayer)) { 
					return GameConstant.ADVANTAGE + GameUtil.playerWithHighestScore(firstPlayer,secondPlayer); 
				}

				if (GameUtil.hasWinner(firstPlayer,secondPlayer)) {
					return GameUtil.playerWithHighestScore(firstPlayer,secondPlayer) + GameConstant.WINS;
				}
				
				if(playerOneScore == playerTwoScore) {
					return GameUtil.getScore(secondPlayer.getPlayerScore()) + GameConstant.ALL;
				}
			} else {
				throw new IllegalArgumentException(GameConstant.SCORE_EXCEPTION);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException(GameConstant.SCORE_EXCEPTION);
		}
		return GameUtil.getScore(firstPlayer.getPlayerScore()) + GameConstant.UNDERSCORE + GameUtil.getScore(secondPlayer.getPlayerScore());
	}

}
