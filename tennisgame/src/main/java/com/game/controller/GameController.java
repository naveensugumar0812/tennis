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

import com.game.service.GameStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NaveenSugumar
 *
 */
@RestController
@RequestMapping("/game")
public class GameController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private GameStrategy tennisGameScore;
	
	@GetMapping(value = "/getScore" )
	public String getScore(@RequestParam("scoreOne") String firstScore, @RequestParam("scoreTwo") String secondScore) {
		LOGGER.info("GameController : getScore : Start");
		String displayScore="";
		try {
		displayScore= tennisGameScore.generateScore(firstScore, secondScore);
		} catch(IllegalArgumentException exception) {
			LOGGER.info("GameController : getScore : End1");
			return exception.getMessage();
		}
		LOGGER.info("GameController : getScore : End");
		return displayScore;
	}

}
