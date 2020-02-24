package com.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.game.controller.GameController;

@SpringBootTest
class GameApplicationTests {
	@InjectMocks
	GameController tc;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testController() {
		//assertEquals("4", tc.getScore());
	}

}
