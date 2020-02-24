package com.game.util;

import java.util.HashMap;
import java.util.Map;

public enum TennisGameScore {
	Love(0),Fifteen(1),Twenty(2),Thirty(3),Forty(4);

	private int value;

	private static final Map<Integer, TennisGameScore> scoreDescription = new HashMap<Integer, TennisGameScore>();

	static {

		for (TennisGameScore scoreType : TennisGameScore.values()) {
			scoreDescription.put(scoreType.value, scoreType);
		}
	}

	private TennisGameScore(int value) {
		this.value=value;
	}

	public int getValue() {
		return value;
	}
	
	/**
	 * Method to get Enum TennisGameScore name for given key
	 * @param key
	 * @return
	 */
	public static TennisGameScore getName(int scoreKey) {
		return (TennisGameScore)scoreDescription.get(scoreKey);
	}

}
