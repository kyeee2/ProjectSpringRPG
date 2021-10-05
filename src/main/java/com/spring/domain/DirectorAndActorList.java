package com.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorAndActorList {
	private String image;		// 사진
	private String staff;		// 감독 or 주연 or 조연
	private String name;		// 배우 이름
	@JsonProperty("actname")
	private String actName;		// 역할 이름
}
