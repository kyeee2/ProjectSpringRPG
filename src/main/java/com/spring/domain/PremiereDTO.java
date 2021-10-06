package com.spring.domain;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "premiere")
// 시사회 정보
public class PremiereDTO {
	private int uid;		// 시사회 고유번호
	private String title;	// 시사회 제목
	private String photo;	// 시사회 사진
	private String content;	// 시사회 내용
}
