package com.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.spring.movieAPI.DirectorAndActorList;
import com.spring.movieAPI.MovieInfoDTO;

@Service
public class MovieCrawlingService {
	
	// 현재 상영작 중 1-5위 영화 제목
	public List<String> titleShowing() {
		String url = "https://movie.naver.com/movie/running/current.naver#"; // 네이버 박스오피스 순위
		Document doc = null; 
		
		try { 
			doc = Jsoup.connect(url).get(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		
		// 현재 상영 영화 순위를 리스트로 가진 태그를 가져오기
		Elements element = doc.select("#content > div.article > div:nth-child(1) > div.lst_wrap > ul");
		
		// 현재 박스오피트 순위를 리스트로 뽑고 5개만 출력하기
		List<Element> titleList = element.select("li > dl > dt > a");
		
		List<String> titleShowing = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			// 문자로 변환해서 넣어주기
			String title_temp = titleList.get(i).text();
			
			titleShowing.add(title_temp);
		}
		
		return titleShowing;
	
	} // end titleShowing()
	
	// 현재 상영작 중 1-5위 영화 포스터
	public List<String> posterShowing() {
		String url = "https://movie.naver.com/movie/running/current.naver#"; // 네이버 박스오피스 순위
		Document doc = null; 
		
		try { 
			doc = Jsoup.connect(url).get(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		
		// 현재 상영 영화 순위를 리스트로 가진 태그를 가져오기
		Elements element = doc.select("#content > div.article > div:nth-child(1) > div.lst_wrap > ul");
		
		// 현재 박스오피트 순위를 리스트로 뽑고 5개만 출력하기
		List<Element> posterList = element.select("li > div > a > img");
		
		List<String> posterShowing = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			// 문자로 변환해서 넣어주기
			String poster_temp = posterList.get(i).attr("src");
			
			posterShowing.add(poster_temp);
		}
		
		return posterShowing;
		
	} // end posterShowing()

	// 현재 상영작 중 1-5위 영화 정보 링크
	public List<String> linkShowing() {
		String url = "https://movie.naver.com/movie/running/current.naver#"; // 네이버 박스오피스 순위
		Document doc = null; 
		
		try { 
			doc = Jsoup.connect(url).get(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		
		// 현재 상영 영화 순위를 리스트로 가진 태그를 가져오기
		Elements element = doc.select("#content > div.article > div:nth-child(1) > div.lst_wrap > ul");
		
		// 현재 박스오피트 순위를 리스트로 뽑고 5개만 출력하기
		List<Element> links = element.select("li > div > a");
		
		List<String> codeShowing = new ArrayList<String>();
		for(int i = 0; i < 5; i++) {
			// 문자로 변환해서 넣어주기
			String code_temp = links.get(i).attr("href");
			
			codeShowing.add(code_temp.substring(code_temp.indexOf("=") + 1));	// url에서 code 값만 가져오기
		}
		return codeShowing;
		
	} // end linkShowing()

	// 현재 상영 예정 최근순으로 10개 영화 제목
	public List<String> titleUpcomming() {
		String url = "https://movie.naver.com/movie/running/premovie.naver?festival=N&order=open";  // 네이버 개봉예정작들이 나오는 페이지
		Document doc = null; 
		
		try { 
			doc = Jsoup.connect(url).get(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		
		List<String> titleUpcomming = new ArrayList<String>();
			
		// 현재 상영 예정작을 리스트로 가진 태그를 가져오기
		for(int i = 0; i < 3; i++) {
			Elements element = doc.select("#content > div.article > div:nth-child(1) > div:nth-child(" + (5 + i) + ") > ul");
					
			// 현재 상영 예정작을 리스트로 뽑고 개수만큼 출력하기
			List<Element> titleList = element.select("li > dl > dt > a");
			
			for(int j = 0; j < titleList.size(); j++) {
				// 문자로 변환해서 넣어주기
				String title_temp = titleList.get(j).text();
				
				titleUpcomming.add(title_temp);
			}
		}
		
		return titleUpcomming;
		
	} // end titleUpcomming()
	
	// 현재 상영 예정 최근순으로 10개 영화 포스터
	public List<String> posterUpcomming() {
		String url = "https://movie.naver.com/movie/running/premovie.naver?festival=N&order=open"; // 네이버 개봉예정작들이 나오는 페이지
		Document doc = null; 
		
		try { 
			doc = Jsoup.connect(url).get(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 

		List<String> posterUpcomming = new ArrayList<String>();
		// 현재 상영 예정작을 리스트로 가진 태그를 가져오기
		for(int i = 0; i < 3; i++) {
			Elements element = doc.select("#content > div.article > div:nth-child(1) > div:nth-child(" + (5 + i) + ") > ul");
		
			// 현재 상영 예정작을 리스트로 뽑고 10개만 출력하기
			List<Element> posterList = element.select("li > div > a > img");
			
			for(int j = 0; j < posterList.size(); j++) {
				// 문자로 변환해서 넣어주기
				String poster_temp = posterList.get(j).attr("src");
				
				posterUpcomming.add(poster_temp);
			}
		}
		
		return posterUpcomming;
		
	} // end posterUpcomming()
	
	// 현재 상영 예정 최근순으로 10개 영화 정보 링크
	public List<String> linkUpcomming() {
		String url = "https://movie.naver.com/movie/running/premovie.naver?festival=N&order=open"; // 네이버 개봉예정작들이 나오는 페이지
		Document doc = null; 
		
		try { 
			doc = Jsoup.connect(url).get(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 

		List<String> codeUpcomming = new ArrayList<String>();
		// 현재 상영 예정작을 리스트로 가진 태그를 가져오기
		for(int i = 0; i < 3; i++) {
			Elements element = doc.select("#content > div.article > div:nth-child(1) > div:nth-child(" + (5 + i) + ") > ul");
		
			// 현재 상영 예정작을 리스트로 뽑고 10개만 출력하기
			List<Element> lists = element.select("li > div > a");
			
			for(int j = 0; j < lists.size(); j++) {
				// 문자로 변환해서 넣어주기
				String code_temp = lists.get(j).attr("href");
				
				codeUpcomming.add(code_temp.substring(code_temp.indexOf("=") + 1));		// url에서 code 값만 가져오기
			}
		}
		
		return codeUpcomming;
		
	} // end linkUpcomming()

	// 영화 상세 페이지에 들어갈 정보 출력
	public MovieInfoDTO getMovieInfo(String code) {
		String url = "https://movie.naver.com/movie/bi/mi/basic.naver?code=" + code; // 네이버 개봉예정작들이 나오는 페이지
		Document doc = null; 
		
		try { 
			doc = Jsoup.connect(url).get(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		MovieInfoDTO dto = new MovieInfoDTO();	// 영화 정보 세팅 준비
		
		// 영화 제목
		Elements elements = doc.select("#content > div.article > div.mv_info_area > div.mv_info > h3 > a:nth-child(1)");
		dto.setMovieName(elements.get(0).text());
		
		// 영화 포스터
		elements = doc.select("#content > div.article > div.mv_info_area > div.poster > a > img");
		String poster;
		if(elements.size() > 0) {
			poster = elements.get(0).attr("src");
		} else {
			poster = "https://ssl.pstatic.net/static/movie/2012/06/dft_img203x290.png";
		}
		dto.setPoster(poster);
		
		// 별점
		// 아직 개봉 전인건 없을 수 있으므로 없으면 0.00으로
		elements = doc.select("#actualPointPersentBasic > div > span > span");
		String star = "";
//		if(elements.html().equals("")) {	// 별점칸이 아예 없다면
//			star = "0.00점";
//		} else if (elements.text().equals("관람객 평점 없음")) { // 아직 별점이 없다면
//			star = "0.00점";
//		} else { // 별점이 있다면
//			star = elements.get(0).text().substring(7);
//		}
		if(elements.size() == 0) {	// 별점칸이 아예 없다면
			star = "0.00점";
		} else if (elements.text().equals("관람객 평점 없음")) { // 아직 별점이 없다면
			star = "0.00점";
		} else { // 별점이 있다면
			star = elements.get(0).text().substring(7);
		}
		dto.setStar(star);
		
		// 영화 장르
		elements = doc.select("#content > div.article > div.mv_info_area > div.mv_info > dl > dd:nth-child(2) > p > span:nth-child(1)");
		String genre = "";
		List<Element> element = elements.select("a");
		for(int i = 0; i < element.size(); i++) {
			if(i != 0) genre += ", ";	// 마지막꺼 빼고 ',' 붙이기
			genre += element.get(i).text();
		}
		dto.setGenre(genre);
		
		// 국가
		elements = doc.select("#content > div.article > div.mv_info_area > div.mv_info > dl > dd:nth-child(2) > p > span:nth-child(2)");
		String nation = "";
		element = elements.select("a");
		for(int i = 0; i < element.size(); i++) {
			if(i != 0) nation += ", ";	// 마지막꺼 빼고 ',' 붙이기
			nation += element.get(i).text();
		}
		dto.setNation(nation);
		
		// 러닝타임
		elements = doc.select("#content > div.article > div.mv_info_area > div.mv_info > dl > dd:nth-child(2) > p > span:nth-child(3)");
		String runningTime = "";
		if(elements.size() > 0) {
			runningTime = elements.get(0).text();
		}
		dto.setRunningTime(runningTime);
		
		// 개봉일
		elements = doc.select("#content > div.article > div.mv_info_area > div.mv_info > dl > dd:nth-child(2) > p > span:nth-child(4)");
		String openDate = "";
		element = elements.select("a");
		for(int i = 0; i < element.size(); i++) {
			openDate += element.get(i).text();
		}
		dto.setOpenDate(openDate);
		
		// 시청 연령
		elements = doc.select("#content > div.article > div.mv_info_area > div.mv_info > dl > dd:nth-child(8) > p > a");
		String viewingAge = "";
		if(elements.size() > 0) {
			viewingAge = elements.get(0).text();
		}
		dto.setViewingAge(viewingAge);
		
		// 줄거리
		elements = doc.select("#content > div.article > div.section_group.section_group_frst > div:nth-child(1) > div > div.story_area");
		String summary = "";
		if(elements.size() <= 0) {
		} else if(elements.get(0).html().indexOf("button") >= 0) {
			summary = elements.get(0).html().substring(0, elements.get(0).html().indexOf("button") - 2);
		} else {
			summary = elements.get(0).html();
		}
		dto.setSummary(summary);
		
		// 예고편
		// 이건 유투브 API 따올거니까 비어있어도 되겠군!
		// TODO
		
		// 감독&배우
		List<DirectorAndActorList> diAndActorList = new ArrayList<DirectorAndActorList>();
		elements = doc.select("#content > div.article > div.section_group.section_group_frst > div > div.people > ul");
		element = elements.select("li");
		for(Element ele : element) {
			String image = ele.select("a:nth-child(1) > img").get(0).attr("src");
			String name = ele.select("a:nth-child(2)").get(0).text();
			String staff = ele.select("dl > dt").get(0).text(); 
			String actName = "";
			if(ele.select("dl > dd").size() > 0) {
				actName = ele.select("dl > dd").get(0).text();
			}
			
			DirectorAndActorList directorAndActorDTO = new DirectorAndActorList(image, staff, name, actName);
			diAndActorList.add(directorAndActorDTO);
		}
		dto.setDirectorActor(diAndActorList);
		
		return dto;
	} // end getMovieInfo(code);

} // end MovieInfoService()
