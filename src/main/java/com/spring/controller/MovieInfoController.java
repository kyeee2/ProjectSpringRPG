package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.MovieCrawlingService;

@Controller
@RequestMapping("/movieInfo")
public class MovieInfoController {
	
	MovieCrawlingService movieInfoService;
	
	@Autowired
	public void setMovieInfoService(MovieCrawlingService movieInfoService) {
		this.movieInfoService = movieInfoService;
	}

	@RequestMapping({"", "/"})
	public String movieInfo(Model model) {
		
		// 박스오피스 순위 5개
		//System.out.println("현재 상영 영화 순위 1 ~ 5");
		
		model.addAttribute("titleShowing", movieInfoService.titleShowing());
		model.addAttribute("posterShowing", movieInfoService.posterShowing());
		model.addAttribute("codeShowing", movieInfoService.linkShowing());

		// 개봉예정작 10개
		//System.out.println("개봉 예정 영화 1 ~ 10");
		
		model.addAttribute("titleUpcomming", movieInfoService.titleUpcomming());
		model.addAttribute("posterUpcomming", movieInfoService.posterUpcomming());
		model.addAttribute("codeUpcomming", movieInfoService.linkUpcomming());
		
		return "/movieInfo/main";
		
	} // end movieInfo(Model)
	
	@RequestMapping("view")
	public String movieInfoView(String code) {
		return "/movieInfo/view";
	} // end movieInfoView(movieName, model)

} // end MovieInfoController()
