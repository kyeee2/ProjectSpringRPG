package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.domain.MovieDatasDTO;
import com.spring.domain.MovieInfoDTO;
import com.spring.service.MovieApiService;
import com.spring.service.MovieCrawlingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AjaxMovieInfoController {
	
	MovieApiService movieApiService;
	MovieCrawlingService movieCrawlingService;
	
	@Autowired
	public void setMovieApiService(MovieApiService movieApiService) {
		this.movieApiService = movieApiService;
	}
    
	@Autowired
	public void setMovieCrawlingService(MovieCrawlingService movieCrawlingService) {
		this.movieCrawlingService = movieCrawlingService;
	}
    
	// 네이버 API 사용한 결과 출력
    @GetMapping("/movieInfo/naver/api/{movieName}")
    public MovieDatasDTO movieData(@PathVariable("movieName") String movieName) {
        return movieApiService.requestMovie(movieName); 
    }
    
    // 영화 정보 페이지에 나타낼 크롤링 결과 출력
    @GetMapping("/movieInfo/naver/crawling/{code}")
    public MovieInfoDTO movieInfo(@PathVariable("code") String code) {
    	return movieCrawlingService.getMovieInfo(code);
    }

}
