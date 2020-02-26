package com.game;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.game.exception.TennisGameException;
import com.game.model.Player;
import com.game.service.impl.TennisGame;
import com.game.util.GameConstant;
import com.game.util.GameUtil;

@SpringBootTest
public class TennisGameTest {

	@InjectMocks
	TennisGame game;

	public TennisGameTest() {
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		game.setFirstPlayer(new Player());
		game.setSecondPlayer(new Player());
		game.getFirstPlayer().setPlayerName(GameConstant.PLAYER_ONE_NAME);
		game.getSecondPlayer().setPlayerName(GameConstant.PLAYER_TWO_NAME);
	}

	// Negative case testing starts

	@Test(expected = TennisGameException.class)
	public void testAlphaInputParam() {
		game.getGameResult("aa ss ", "asdas");
	}

	@Test(expected = TennisGameException.class)
	public void testNegativeCaseParam() {
		game.getGameResult("-1", "-1");
	}
	
	@Test
	public void testLoveAll() {
		assertEquals(GameConstant.SCORE_LOVE + GameConstant.ALL, game.getGameResult("0", "0"));
	}

	// Negative case testing ends

	// TestCase for methods in TennisGame.java start

	/*	@Test
	public void testLoveAll() {
		assertEquals(GameConstant.SCORE_LOVE + GameConstant.ALL, game.getGameResult("0", "0"));
	}

	--@Test
	public void testFifteenAll() {
		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.ALL, game.getGameResult("1", "1"));
	}

	@Test
	public void testThirtyAll() {
		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.ALL, game.getGameResult("2", "2"));
	}--

	@Test
	public void testDeuce() {
		assertEquals(GameConstant.DEUCE, game.getGameResult("3", "3"));
	}

	@Test
	public void testPlayerOneAdvantage() {
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_ONE_NAME, game.getGameResult("4", "3"));
	}

	@Test
	public void testPlayerOneWins() {
		assertEquals(GameConstant.PLAYER_ONE_NAME + GameConstant.WINS, game.getGameResult("3", "1"));
	}

	@Test
	public void testPlayerTwoWinsSecondBall() {
		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.UNDERSCORE + GameConstant.SCORE_FIFTEEN,
				game.getGameResult("2", "1"));
	} */

	// TestCase for methods in TennisGame.java End

	// Testcase for methods in GameUtil.java Start

	// Deuce Method Check Start

	@Test
	public void testcheckDeuce() {
		game.getFirstPlayer().setPlayerScore(3);
		game.getSecondPlayer().setPlayerScore(3);
		assertEquals(true,GameUtil.checkForDeuce(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testDeuceWithPlayerOneScoreLtThree() {
		game.getFirstPlayer().setPlayerScore(2);
		game.getSecondPlayer().setPlayerScore(3);
		assertEquals(false,GameUtil.checkForDeuce(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testDeuceWithPlayersSCoreNotEqual() {
		game.getFirstPlayer().setPlayerScore(4);
		game.getSecondPlayer().setPlayerScore(5);
		assertEquals(false,GameUtil.checkForDeuce(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	// Deuce Method Check End

	//GetLeadingScorer Method check Start

	@Test
	public void testPlayerOneWithMaxScore() {
		game.getFirstPlayer().setPlayerScore(5);
		game.getSecondPlayer().setPlayerScore(4);
		assertEquals(GameConstant.PLAYER_ONE_NAME,GameUtil.getLeadingScorer(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithMaxScore() {
		game.getFirstPlayer().setPlayerScore(1);
		game.getSecondPlayer().setPlayerScore(2);
		assertEquals(GameConstant.PLAYER_TWO_NAME,GameUtil.getLeadingScorer(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	//GetLeadingScorer Method check End

	//CheckForAdvantage Method check Start

	@Test
	public void testPlayerOneWithAdvantage() {
		game.getFirstPlayer().setPlayerScore(5);
		game.getSecondPlayer().setPlayerScore(4);
		assertEquals(true,GameUtil.checkForAdvantage(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithAdvantage() {
		game.getFirstPlayer().setPlayerScore(4);
		game.getSecondPlayer().setPlayerScore(5);
		assertEquals(true,GameUtil.checkForAdvantage(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneWithOutAdvantage() {
		game.getFirstPlayer().setPlayerScore(5);
		game.getSecondPlayer().setPlayerScore(5);
		assertEquals(false,GameUtil.checkForAdvantage(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithOutAdvantage() {
		game.getFirstPlayer().setPlayerScore(4);
		game.getSecondPlayer().setPlayerScore(6);
		assertEquals(false,GameUtil.checkForAdvantage(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneNotWithMinAdvScore() {
		game.getFirstPlayer().setPlayerScore(2);
		game.getSecondPlayer().setPlayerScore(1);
		assertEquals(false,GameUtil.checkForAdvantage(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoNotWithMinAdvScore() {
		game.getFirstPlayer().setPlayerScore(0);
		game.getSecondPlayer().setPlayerScore(1);
		assertEquals(false,GameUtil.checkForAdvantage(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	//CheckForAdvantage Method check End

	//Test AddScore Method check Start

	@Test
	public void testaddScore() {
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(1,game.getFirstPlayer().getPlayerScore());
	}

	//Test AddScore Method check End
	
	//Test SetPlayerInfo Method check Start

		@Test
		public void testSetPlayerInfo() {
			Player player= GameUtil.setPlayerInfo(2, GameConstant.PLAYER_ONE_NAME);
			assertEquals(GameConstant.PLAYER_ONE_NAME,player.getPlayerName());
		}

		//Test SetPlayerInfo Method check End

	//CheckForWinner Method check Start

	@Test
	public void testPlayerOneAsWinner() {
		game.getFirstPlayer().setPlayerScore(5);
		game.getSecondPlayer().setPlayerScore(3);
		assertEquals(true,GameUtil.checkForWinner(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoAsWinner() {
		game.getFirstPlayer().setPlayerScore(3);
		game.getSecondPlayer().setPlayerScore(5);
		assertEquals(true,GameUtil.checkForWinner(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneWithleadingScoreOne() {
		game.getFirstPlayer().setPlayerScore(5);
		game.getSecondPlayer().setPlayerScore(4);
		assertEquals(false,GameUtil.checkForWinner(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithleadingScoreOne() {
		game.getFirstPlayer().setPlayerScore(4);
		game.getSecondPlayer().setPlayerScore(5);
		assertEquals(false,GameUtil.checkForWinner(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneNotWithMinWinningScore() {
		game.getFirstPlayer().setPlayerScore(2);
		game.getSecondPlayer().setPlayerScore(1);
		assertEquals(false,GameUtil.checkForWinner(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoNotWithMinWinningScore() {
		game.getFirstPlayer().setPlayerScore(0);
		game.getSecondPlayer().setPlayerScore(2);
		assertEquals(false,GameUtil.checkForWinner(game.getFirstPlayer(), game.getSecondPlayer()));
	}

	//CheckForWinner Method check End
	
	// Test IsValidScore method Start

	@Test(expected = TennisGameException.class)
	public void testAllNegativeParam() {
		GameUtil.isValidScore(-1, -1);
	}

	@Test(expected = TennisGameException.class)
	public void testNegativeScorePlayerOne() {
		GameUtil.isValidScore(-1, 0);
	}

	@Test(expected = TennisGameException.class)
	public void testNegativeScorePlayerTwo() {
		GameUtil.isValidScore(4, -1);
	}

	@Test(expected = TennisGameException.class)
	public void testPlayerTwoWinMarginGtThree() {
		GameUtil.isValidScore(4, 7);
	}

	@Test(expected = TennisGameException.class)
	public void testPlayerOneWinMarginGtThree() {
		GameUtil.isValidScore(8, 5);
	} 
	
	@Test
	public void testPlayersScoreLtThree() {
		assertEquals(true,GameUtil.isValidScore(1, 2));
	}
	@Test
	public void testPlayersScoreEqThree() {
		assertEquals(true,GameUtil.isValidScore(3, 3));
	}
	
	@Test
	public void testPlayersWithValidScore() {
		assertEquals(true,GameUtil.isValidScore(8, 8));
	} 
	
	// Test IsValidScore method End
	
	// Test getScore method Start
	
	@Test
	public void testScoreLove() {
		assertEquals(GameConstant.SCORE_LOVE,GameUtil.getScore(0));
	}
	
	@Test
	public void testScoreFifteen() {
		assertEquals(GameConstant.SCORE_FIFTEEN,GameUtil.getScore(1));
	}
	
	@Test
	public void testScoreThirty() {
		assertEquals(GameConstant.SCORE_THIRTY,GameUtil.getScore(2));
	}
	
	/*@Test
	public void testScoreForty() {
		assertEquals(GameConstant.SCORE_FORTY,GameUtil.getScore(3));
	}*/
	
	// Test getScore method End


	// Testcase for methods in GameUtil.java End

	/*
	@Test
	public void testScoreFifteenLove() {
		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.UNDERSCORE + GameConstant.SCORE_LOVE,
				game.getGameResult("1", "0"));
	}

	@Test
	public void testPlayerOneWinsSecondBall() {
		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.UNDERSCORE + GameConstant.SCORE_LOVE,
				game.getGameResult("2", "0"));
	}

	@Test
	public void testPlayerTwoWinsSecondBall() {
		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.UNDERSCORE + GameConstant.SCORE_FIFTEEN,
				game.getGameResult("2", "1"));
	}


	@Test
	public void testPlayerTwoWins() {
		assertEquals(GameConstant.PLAYER_TWO_NAME + GameConstant.WINS, game.getGameResult("0", "3"));
	}



	@Test
	public void testPlayerTwoAdvantage() {
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_TWO_NAME, game.getGameResult("3", "4"));
	}



	@Test
	public void testPlayerTwoWinsAfterAdvantage() {
		assertEquals(GameConstant.PLAYER_TWO_NAME + GameConstant.WINS, game.getGameResult("5", "7"));
	}

	@Test
	public void testPlayerOneWinsAfterAdvantage() {
		assertEquals(GameConstant.PLAYER_ONE_NAME + GameConstant.WINS, game.getGameResult("6", "4"));
	}*/

	@Test
	public void testGameOne() {

		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			GameUtil.addScore(game.getFirstPlayer());
		});
		assertEquals(GameConstant.PLAYER_ONE_NAME + GameConstant.WINS, game.getScoreBoard());
	}

	@Test
	public void testGameTwo() {

		assertEquals(GameConstant.SCORE_LOVE + GameConstant.ALL, game.getScoreBoard());
		IntStream.rangeClosed(1, 2).forEach((Integer) -> {
			GameUtil.addScore(game.getFirstPlayer());
		});
		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.UNDERSCORE + GameConstant.SCORE_LOVE,
				game.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> {
			GameUtil.addScore(game.getSecondPlayer());
		});

		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.UNDERSCORE + GameConstant.SCORE_FIFTEEN,
				game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.PLAYER_ONE_NAME + GameConstant.WINS, game.getScoreBoard());
	}



	@Test
	public void testGameThree() {

		assertEquals(GameConstant.SCORE_LOVE + GameConstant.ALL, game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.UNDERSCORE + GameConstant.SCORE_LOVE,
				game.getScoreBoard());
		GameUtil.addScore(game.getSecondPlayer());
		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.ALL, game.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> {
			GameUtil.addScore(game.getSecondPlayer());
		});

		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.UNDERSCORE + GameConstant.SCORE_THIRTY,
				game.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> {
			GameUtil.addScore(game.getFirstPlayer());
		});
		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.ALL, game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_ONE_NAME, game.getScoreBoard());
		GameUtil.addScore(game.getSecondPlayer());
		assertEquals(GameConstant.DEUCE, game.getScoreBoard());
		GameUtil.addScore(game.getSecondPlayer());
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_TWO_NAME, game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.DEUCE, game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_ONE_NAME, game.getScoreBoard());
		GameUtil.addScore(game.getFirstPlayer());
		assertEquals(GameConstant.PLAYER_ONE_NAME + GameConstant.WINS, game.getScoreBoard());
	}

}