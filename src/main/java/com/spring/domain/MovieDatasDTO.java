package com.spring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDatasDTO {
	private int display;
    private Item[] items;

    @Data
    static class Item{
        private String title;	// 영화 타이틀
        private String image;   // 영화 포스터
        private String link;	// 해당 영화 정보 페이지
    }
}
