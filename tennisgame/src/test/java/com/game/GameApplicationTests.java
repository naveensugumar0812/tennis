package com.game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.game.service.impl.TennisGame;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class GameApplicationTests {
	
	@InjectMocks
	TennisGame game;
	
	public GameApplicationTests(){}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	// Negative case testing starts

	@Test(expected=IllegalArgumentException.class)
	public void testAlphaInputParam() {
		game.generateScore("aa ss", "asdas");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAllNegativeParam() {
		game.generateScore("-1", "-1");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegativeScorePlayerOne() {
		game.generateScore("-1", "0");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeScorePlayerTwo() {
		game.generateScore("2", "-4");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPlayerTwoWinMarginGtThree() {
		game.generateScore("6", "9");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlayerOneWinMarginGtThree() {
		game.generateScore("8", "5");
	}
	
	// Negative case testing ends

	@Test
	public void testLoveAll() {
		assertEquals("Love all",game.generateScore("0", "0"));
	}
	
	@Test
	public void testPlayerOneWins() {
		assertEquals("Fifteen-Love",game.generateScore("1", "0"));
	}
	
	@Test
	public void testPlayerOneWinsSecondBall() {
		assertEquals("Thirty-Love",game.generateScore("2", "0"));
	}
	
	@Test
	public void testPlayerTwoWinsSecondBall() {
		assertEquals("Thirty-Fifteen",game.generateScore("2", "1"));
	}

	@Test
	public void testFifteenAll() {
		assertEquals("Fifteen all",game.generateScore("1", "1"));
	}
	
	@Test
	public void testThirtyAll() {
		assertEquals("Thirty all",game.generateScore("2", "2"));
	}
	
	@Test
	public void testDeuce() {
		assertEquals("Deuce",game.generateScore("3", "3"));
	}
	
	
	@Test
	public void testPlayerOneAdvantage() {
		assertEquals("Advantage PlayerOne",game.generateScore("4", "3"));
	}
	
	@Test
	public void testPalyerTwoAdvantage() {
		assertEquals("Advantage PlayerTwo",game.generateScore("3", "4"));
	}
	
	@Test
	public void testPalyerOneWins() {
		assertEquals("PlayerOne wins",game.generateScore("3", "1"));
	}
	
	@Test
	public void testPalyerTwoWins() {
		assertEquals("PlayerTwo wins",game.generateScore("0", "3"));
	}
	
	@Test
	public void testPalyerTwoWinsAfterAdvantage() {
		assertEquals("PlayerTwo wins",game.generateScore("5", "7"));
	}
	
	@Test
	public void testPalyerOneWinsAfterAdvantage() {
		assertEquals("PlayerOne wins",game.generateScore("6", "4"));
	}

}