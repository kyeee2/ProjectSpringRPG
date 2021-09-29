package com.spring.movieAPI;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 영화 상세 페이지에 들어갈 정보
public class MovieInfoDTO {
	@JsonProperty("moviename")
	private String movieName;			// 영화 제목
	private String poster;				// 영화 포스터
	private String star;				// 별점
	private String genre;				// 영화 장르
	private String nation;				// 국가
	@JsonProperty("runningtime")
	private String runningTime;			// 러닝타임
	@JsonProperty("opendate")
	private String openDate;			// 개봉일
	@JsonProperty("viewingage")
	private String viewingAge;			// 시청연령
	private String summary;				// 줄거리
	private String trailer;				// 예고편
	@JsonProperty("directorAndActor")
	private List<DirectorAndActorList> directorActor;   // 감독&배우
} // end MovieInfoDTO()
