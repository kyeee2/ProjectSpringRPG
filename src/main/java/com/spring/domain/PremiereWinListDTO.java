package com.spring.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremiereWinListDTO {
	private int uid;
	private String title;
	private String content;
	private LocalDateTime dateTime;
	
	public String getDatetime(){
		if(this.dateTime == null) {
			return "";
		}
		return this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
