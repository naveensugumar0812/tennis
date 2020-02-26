package com.game.service.impl;

import org.springframework.stereotype.Repository;

import com.game.exception.TennisGameException;
import com.game.model.Player;
import com.game.service.Game;
import com.game.util.GameConstant;
import com.game.util.GameUtil;

@Repository
public class TennisGame implements Game {

	private Player firstPlayer;
	private Player secondPlayer;

	public String getGameResult(String firstScore, String secondScore) {
		String gameResult = "";

		try {
			int firstPlayerScore = Integer.parseInt(firstScore);
			int secondPlayerScore = Integer.parseInt(secondScore);

			if (GameUtil.isValidScore(firstPlayerScore, secondPlayerScore)) {
				firstPlayer = GameUtil.setPlayerInfo(firstPlayerScore, GameConstant.PLAYER_ONE_NAME);
				secondPlayer = GameUtil.setPlayerInfo(secondPlayerScore, GameConstant.PLAYER_TWO_NAME);

				gameResult = getScoreBoard();
			}
		} catch (NumberFormatException numberFormatException) {
			throw new TennisGameException(GameConstant.SCORE_EXCEPTION);
		} catch (Exception exception) {
			throw new TennisGameException(GameConstant.SCORE_EXCEPTION);
		}

		return gameResult;
	}

	public String getScoreBoard() {

		if (GameUtil.checkForDeuce(firstPlayer, secondPlayer)) {
			return GameConstant.DEUCE;
		}

		if (GameUtil.checkForAdvantage(firstPlayer, secondPlayer)) {
			return GameConstant.ADVANTAGE + GameUtil.getLeadingScorer(firstPlayer, secondPlayer);
		}

		if (GameUtil.checkForWinner(firstPlayer, secondPlayer)) {
			return GameUtil.getLeadingScorer(firstPlayer, secondPlayer) + GameConstant.WINS;
		}

		if (firstPlayer.getPlayerScore() == secondPlayer.getPlayerScore()) {
			return GameUtil.getScore(secondPlayer.getPlayerScore()) + GameConstant.ALL;
		}
		return GameUtil.getScore(firstPlayer.getPlayerScore()) + GameConstant.UNDERSCORE
				+ GameUtil.getScore(secondPlayer.getPlayerScore());
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
