/**
 * 
 */
package com.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.service.Game;

/**
 * @author NaveenSugumar
 *
 */
@RestController
@RequestMapping("/game")
public class GameController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private Game tennisGame;

	@GetMapping(value = "/getResult")
	public String getResult(@RequestParam("scoreOne") String firstScore, @RequestParam("scoreTwo") String secondScore) {
		LOGGER.info("GameController : getGameResult : Start");

		String displayResult = "";

		try {
			displayResult = tennisGame.getGameResult(firstScore, secondScore);
		} catch (Exception exception) {
			LOGGER.error("GameController : getGameResult : Exception");
			return exception.getMessage();
		}

		LOGGER.info("GameController : getGameResult : End");
		return displayResult;
	}

}
