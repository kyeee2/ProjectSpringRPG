package com.spring.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AjaxPremiereWinList extends AjaxBoardResult {
	
	@JsonProperty("data")
	List<PremiereWinDTO> list;
	
	int page;
	
	@JsonProperty("totalpage")
	int totalPage;
	
	@JsonProperty("totalcnt")
	int totalCnt;
	
	@JsonProperty("writepages")
	int writePages;
	
	@JsonProperty("pagerows")
	int pageRows;
	
}
