package com.spring.domain;



import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDTO {
	private int prouid;       // bf_uid
	private String source;  // bf_source
	private String file;     // bf_file
	private boolean isImage;  // 이미지 여부
	}
