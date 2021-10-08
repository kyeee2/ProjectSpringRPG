package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MapController {

	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@RequestMapping(value="/map", method = RequestMethod.GET)
	public String kakaoMap() {
		return "map/kakaomap";
	}
}
