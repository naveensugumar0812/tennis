package com.game.service.impl;

import org.springframework.stereotype.Repository;

import com.game.exception.IllegalScorePassedException;
import com.game.model.Player;
import com.game.service.GameStrategy;
import com.game.util.GameConstant;
import com.game.util.GameUtil;

@Repository
public class TennisGame implements GameStrategy {

	private Player firstPlayer;
	private Player secondPlayer;

	public String setPlayerScore(String firstScore, String secondScore) {
		String gameScore="";
		try {
			int playerOneScore=Integer.parseInt(firstScore);
			int playerTwoScore=Integer.parseInt(secondScore);

			firstPlayer= GameUtil.setPlayerInfo(playerOneScore, GameConstant.PLAYER_ONE_NAME);
			secondPlayer=GameUtil.setPlayerInfo(playerTwoScore, GameConstant.PLAYER_TWO_NAME);
			if(GameUtil.isValidScore(playerOneScore, playerTwoScore)){
				gameScore= getScoreBoard();
			} else {
				throw new IllegalScorePassedException(GameConstant.SCORE_EXCEPTION);
			}
		} catch(Exception ex) {
			throw new IllegalScorePassedException(GameConstant.SCORE_EXCEPTION);
		}

		return gameScore;
	}

	public String getScoreBoard() {

		if (GameUtil.checkForDeuce(firstPlayer,secondPlayer)) {
			return GameConstant.DEUCE;
		}

		if (GameUtil.checkForAdvantage(firstPlayer,secondPlayer)) { 
			return GameConstant.ADVANTAGE + GameUtil.getLeadingScorer(firstPlayer,secondPlayer); 
		}

		if (GameUtil.checkForWinner(firstPlayer,secondPlayer)) {
			return GameUtil.getLeadingScorer(firstPlayer,secondPlayer) + GameConstant.WINS;
		}

		if(firstPlayer.getPlayerScore() == secondPlayer.getPlayerScore()) {
			return GameUtil.getScore(secondPlayer.getPlayerScore()) + GameConstant.ALL;
		}
		return GameUtil.getScore(firstPlayer.getPlayerScore()) + GameConstant.UNDERSCORE + GameUtil.getScore(secondPlayer.getPlayerScore());
	}

	/**
	 * @return the firstPlayer
	 */
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * @param firstPlayer the firstPlayer to set
	 */
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	/**
	 * @return the secondPlayer
	 */
	public Player getSecondPlayer() {
		return secondPlayer;
	}

	/**
	 * @param secondPlayer the secondPlayer to set
	 */
	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

}
