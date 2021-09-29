package com.spring.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.movieAPI.MovieDatasDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieApiService {
	
	private final RestTemplate restTemplate;

    private final String CLIENT_ID = "K5oOG65LrhtyHZNqWtVb";	// ID
    private final String CLIENT_SECRET = "11kY1ID3d9"; 			//key

    private  final String OpenNaverMoviekUrl_getMovieInfo = "https://openapi.naver.com/v1/search/movie.json?query=";

    public MovieDatasDTO requestMovie(String movieName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(OpenNaverMoviekUrl_getMovieInfo + movieName, HttpMethod.GET, entity, MovieDatasDTO.class).getBody();
    }
}
