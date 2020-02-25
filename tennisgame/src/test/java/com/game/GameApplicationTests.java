package com.game;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.game.exception.IllegalScorePassedException;
import com.game.model.Player;
import com.game.service.impl.TennisGame;
import com.game.util.GameConstant;
import com.game.util.GameUtil;

@SpringBootTest
public class GameApplicationTests {

	@InjectMocks
	TennisGame game;

	public GameApplicationTests(){}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		game.setFirstPlayer(new Player());
		game.setSecondPlayer(new Player());
		game.getFirstPlayer().setPlayerName(GameConstant.PLAYER_ONE_NAME);
		game.getSecondPlayer().setPlayerName(GameConstant.PLAYER_TWO_NAME);
	}

	// Negative case testing starts

	@Test(expected=IllegalScorePassedException.class)
	public void testAlphaInputParam() {
		game.setPlayerScore("aa ss ", "asdas");
	}

	@Test(expected=IllegalScorePassedException.class)
	public void testAllNegativeParam() {
		game.setPlayerScore("-1", "-1");
	}

	@Test(expected=IllegalScorePassedException.class)
	public void testNegativeScorePlayerOne() {
		game.setPlayerScore("-1", "0");
	}

	@Test(expected=IllegalScorePassedException.class)
	public void testNegativeScorePlayerTwo() {
		game.setPlayerScore("2", "-4");
	}

	@Test(expected=IllegalScorePassedException.class)
	public void testPlayerTwoWinMarginGtThree() {
		game.setPlayerScore("6", "9");
	}

	@Test(expected=IllegalScorePassedException.class)
	public void testPlayerOneWinMarginGtThree() {
		game.setPlayerScore("8", "5");
	}

	// Negative case testing ends

	@Test
	public void testLoveAll() {
		assertEquals(GameConstant.SCORE_LOVE+GameConstant.ALL ,game.setPlayerScore("0", "0"));
	}

	@Test
	public void testScoreFifteenLove() {
		assertEquals(GameConstant.SCORE_FIFTEEN+GameConstant.UNDERSCORE+GameConstant.SCORE_LOVE,game.setPlayerScore("1", "0"));
	}

	@Test
	public void testPlayerOneWinsSecondBall() {
		assertEquals(GameConstant.SCORE_THIRTY+GameConstant.UNDERSCORE+GameConstant.SCORE_LOVE,game.setPlayerScore("2", "0"));
	}

	@Test
	public void testPlayerTwoWinsSecondBall() {
		assertEquals(GameConstant.SCORE_THIRTY+GameConstant.UNDERSCORE+GameConstant.SCORE_FIFTEEN,game.setPlayerScore("2", "1"));
	}

	@Test
	public void testFifteenAll() {
		assertEquals(GameConstant.SCORE_FIFTEEN+GameConstant.ALL,game.setPlayerScore("1", "1"));
	}

	@Test
	public void testThirtyAll() {
		assertEquals(GameConstant.SCORE_THIRTY+GameConstant.ALL,game.setPlayerScore("2", "2"));
	}

	@Test
	public void testDeuce() {
		assertEquals(GameConstant.DEUCE,game.setPlayerScore("3", "3"));
	}


	@Test
	public void testPlayerOneAdvantage() {
		assertEquals(GameConstant.ADVANTAGE+GameConstant.PLAYER_ONE_NAME,game.setPlayerScore("4", "3"));
	}

	@Test
	public void testPlayerTwoAdvantage() {
		assertEquals(GameConstant.ADVANTAGE+GameConstant.PLAYER_TWO_NAME,game.setPlayerScore("3", "4"));
	}

	@Test
	public void testPlayerOneWins() {
		assertEquals(GameConstant.PLAYER_ONE_NAME+GameConstant.WINS,game.setPlayerScore("3", "1"));
	}

	@Test
	public void testPlayerTwoWins() {
		assertEquals(GameConstant.PLAYER_TWO_NAME+GameConstant.WINS,game.setPlayerScore("0", "3"));
	}

	@Test
	public void testPlayerTwoWinsAfterAdvantage() {
		assertEquals(GameConstant.PLAYER_TWO_NAME+GameConstant.WINS,game.setPlayerScore("5", "7"));
	}

	@Test
	public void testPlayerOneWinsAfterAdvantage() {
		assertEquals(GameConstant.PLAYER_ONE_NAME+GameConstant.WINS,game.setPlayerScore("6", "4"));
	}

	@Test 
	public void testGameOne() {

		IntStream.rangeClosed(1, 3).forEach((Integer) -> { 
			GameUtil.addScore(game.getFirstPlayer());
		});
		assertEquals(GameConstant.PLAYER_ONE_NAME+GameConstant.WINS,game.getScoreBoard());
	}
	
	@Test 
	public void testGameTwo() {

		assertEquals(GameConstant.SCORE_LOVE+GameConstant.ALL,game.getScoreBoard());
		IntStream.rangeClosed(1, 2).forEach((Integer) -> { 
			GameUtil.addScore(game.getFirstPlayer());
		});
		assertEquals(GameConstant.SCORE_THIRTY+GameConstant.UNDERSCORE+GameConstant.SCORE_LOVE,game.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> { 
			GameUtil.addScore(game.getSecondPlayer());
		});
		
		assertEquals(GameConstant.SCORE_THIRTY+GameConstant.UNDERSCORE+GameConstant.SCORE_FIFTEEN,game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.PLAYER_ONE_NAME+GameConstant.WINS,game.getScoreBoard());
	}
	
	/*
	 * Test case to scenario of player winning after score is in deuce and advantage
	 */
	
	@Test 
	public void testGameThree() {

		assertEquals(GameConstant.SCORE_LOVE+GameConstant.ALL,game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.SCORE_FIFTEEN+GameConstant.UNDERSCORE+GameConstant.SCORE_LOVE,game.getScoreBoard());
		GameUtil.addScore(game.getSecondPlayer());
		assertEquals(GameConstant.SCORE_FIFTEEN+GameConstant.ALL,game.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> { 
			GameUtil.addScore(game.getSecondPlayer());
		});
		
		assertEquals(GameConstant.SCORE_FIFTEEN+GameConstant.UNDERSCORE+GameConstant.SCORE_THIRTY,game.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> { 
			GameUtil.addScore(game.getFirstPlayer());
		});
		assertEquals(GameConstant.SCORE_THIRTY+GameConstant.ALL,game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.ADVANTAGE+GameConstant.PLAYER_ONE_NAME,game.getScoreBoard());
		GameUtil.addScore(game.getSecondPlayer());
		assertEquals(GameConstant.DEUCE,game.getScoreBoard());
		GameUtil.addScore(game.getSecondPlayer());
		assertEquals(GameConstant.ADVANTAGE+GameConstant.PLAYER_TWO_NAME,game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.DEUCE,game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.ADVANTAGE+GameConstant.PLAYER_ONE_NAME,game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.PLAYER_ONE_NAME+GameConstant.WINS,game.getScoreBoard());
	}


}