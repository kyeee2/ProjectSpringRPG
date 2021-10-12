	package com.spring.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.CustomerValidator;
import com.spring.config.PrincipalDetails;
import com.spring.domain.CustomerDTO;
import com.spring.service.AjaxBoardService;
import com.spring.service.LoginService;
import com.spring.service.MovieCrawlingService;

@Controller

public class LoginController {

	public LoginController() {
		System.out.println("LoginController() 생성");
	}

	@Autowired
	LoginService loginService;
	
	// 메인페이지용
	MovieCrawlingService movieInfoService;
	AjaxBoardService ajaxBoardService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@Autowired
	public void setMovieInfoService(MovieCrawlingService movieInfoService) {
		this.movieInfoService = movieInfoService;
	}

	@Autowired
	public void setAjaxBoardService(AjaxBoardService ajaxBoardService) {
		this.ajaxBoardService = ajaxBoardService;
	}
	
	public void naverlogin() {	}
	//아이디 로그인
	@GetMapping("/login")
	public String login() {	
	return "/basic/login";	
	}
	@GetMapping(value = "/getKakaoAuthUrl")
	public @ResponseBody String getKakaoAuthUrl(
			HttpServletRequest request) throws Exception {
	
	    
	    
		String reqUrl = 
					"https://kauth.kakao.com/oauth/authorize"
					+ "?client_id=d11a12ee85c98662914e0bac1931a617"
					+ "&redirect_uri=" + URLEncoder.encode("http://localhost:8090/kakaoinfo", "UTF-8")
					+ "&response_type=code";
		
		
		return reqUrl;
	}
	//카카오 연동정보 조회
	@RequestMapping("/kakaoinfo")
	public String oauthKakao(
			@RequestParam(value = "code", required = false) String code
			, Model model) throws Exception {

		System.out.println("#########" + code);
        String access_Token = getAccessToken(code);
        System.out.println("###access_Token#### : " + access_Token);
        
       
        
        String userInfo = getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        
       
        
        
        return "/basic/main"; //본인 원하는 경로 설정
	}
	

	
	
	
    //토큰발급
	public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=d11a12ee85c98662914e0bac1931a617");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:8090/kakaoinfo");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return access_Token;
    }
	
    //유저정보조회
    public String getUserInfo (String access_Token ) throws Exception{

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        
    	CustomerDTO user= new CustomerDTO();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br;
            if(responseCode==200) { // 정상 호출
    	        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	      } else {  // 에러 발생
    	        br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    	      }
    	      String inputLine;
    	      StringBuffer res = new StringBuffer();
    	      while ((inputLine = br.readLine()) != null) {
    	        res.append(inputLine);
    	      }
    	      br.close();
            

            JSONParser parsing = new JSONParser();
            Object obj = parsing.parse(res.toString());
			JSONObject jsonObj = (JSONObject)obj;
			JSONObject properties = (JSONObject)jsonObj.get("properties");
			JSONObject kakao_account = (JSONObject)jsonObj.get("kakao_account");
			JSONObject response2 = (JSONObject)jsonObj.get("response");

            String nickname = (String)properties.get("nickname");
            String id = (String)response2.get("user_id");
           
            String birthday= (String)kakao_account.get("birthday");
           
            user.setNickname(nickname);
            user.setId(id);
            user.setBirthday(Integer.parseInt(birthday));
            
           

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        System.out.println("controller : " + user.getId());
		if(user.getId() != null) {
			loginService.addMember(user);	// 로그인한 정보를 DB에 생성
		}
        return "redirect:/loginOk";
    }
 
	
	@RequestMapping("/joinAgree")
	public String joinAgree() {
		return "/basic/joinAgree";
	}
	@GetMapping("/join")
	public String join(Model model) {
	return "/basic/join";
	}
	@PostMapping("/certified")
	public @ResponseBody String sendSMS(CustomerDTO user) {
		
		String phonenum = user.getPhonenum();
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        
        System.out.println("수신자 번호 : " + phonenum);
        System.out.println("인증번호 : " + numStr);
        loginService.certifiedPhoneNumber(phonenum,numStr);
        return numStr;
  
}	
		
	@PostMapping("/joinOk")
	public String joinOk(HttpServletRequest request, HttpServletResponse response,@ModelAttribute @Valid CustomerDTO user,BindingResult result) throws Exception {
//		String fileUrl = FileHelper.upload("/uploads", file, request);
//		user.setProfile(fileUrl);
//		joinOk(user);
		
		String rawPassword = user.getPw();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPw(encPassword);
		
		if(result.hasErrors()) {   // 에러 있으면
			return "/join";  // 원래 폼으로 돌아가기
		}
		String id = user.getId();
		int checkid = loginService.idChk(id);
		String nickname = user.getNickname();
		int checknick = loginService.nickChk(nickname);
		try {
			if(checkid == 1 || checknick == 1) {
				return "/join";
			}else if(checkid == 0 || checknick == 0) {
				loginService.addMember(user);
			}
			// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
			// 존재하지 않는다면 -> register
		} catch (Exception e) {
			throw new RuntimeException();
		}

		return "redirect:/login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "/basic/logout";
	}
	@RequestMapping(value ="/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String naver_callback(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//, RedirectAttributes redirectAtt
	    String clientId = "MdDnprhEU_V4J7WsPkRu";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "a1VUtxhLxH";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:8090/callback", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	   
		CustomerDTO user= new CustomerDTO();
	    
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        JSONParser parser = new JSONParser();
			Object obj = parser.parse(res.toString());
			JSONObject jsonObj = (JSONObject) obj;

			access_token = (String) jsonObj.get("access_token");
			refresh_token = (String) jsonObj.get("refresh_token");
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    String header = "Bearer " + access_token;
		System.out.println(header);
		if(access_token != null) { // access_token을 잘 받아왔다면
			try {
				String apiurl = "https://openapi.naver.com/v1/nid/me";
				URL url = new URL(apiurl);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Authorization", header);
				
				int responseCode = con.getResponseCode();
				BufferedReader br;
				
				if(responseCode==200) { // 정상 호출
				 br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				
				String inputLine;
				StringBuffer res = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
				}
				 
				br.close();
				JSONParser parsing = new JSONParser();
				Object obj = parsing.parse(res.toString());
				JSONObject jsonObj = (JSONObject)obj;
				JSONObject resObj = (JSONObject)jsonObj.get("response");
				
				System.out.println("컨트롤러 정보 잘 받아졌나? : " + resObj);
				
				//왼쪽 변수 이름은 원하는 대로 정하면 된다. 
				//단, 우측의 get()안에 들어가는 값은 네이버 developer 홈페이지에 써있는 이름으로 써줘야한다.
				/*String name = (String)resObj.get("name");*/
				/*String nickname = (String)resObj.get("nickname");*/
				/*String profile = (String)resObj.get("profile_image");*/
				String birthday = (String)resObj.get("birthday");
				/*String mobile = (String)resObj.get("mobile");*/
				birthday = birthday.replace("-", "");
				String birthyear = (String)resObj.get("birthyear");
				birthyear = birthyear.substring(2, 4);
				birthday = birthyear + birthday;

				
				System.out.println("callback : " + (String)resObj.get("id"));
				
				user.setId((String)resObj.get("id"));
				
				String encPassword = passwordEncoder.encode("movie@@33"); //DB에서 Not null로 처리했기에 임의로 준 값
				user.setPw(encPassword);
				
				user.setName((String) resObj.get("name"));
				user.setNickname((String) resObj.get("nickname"));
				user.setBirthday(Integer.parseInt(birthday));
				String mobile = (String)resObj.get("mobile");
				mobile = mobile.replace("-", "");
				user.setPhonenum(mobile);
				user.setEnable(1);
			        /*member.setBirth((String) response.get("birthday"));*/
				
				System.out.println("custor 정보 : " + user.toString());
				
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		
		System.out.println("controller : " + user.getId());
		if(user.getId() != null) {
			loginService.addMember(user);	// 로그인한 정보를 DB에 생성
		}
		
//		Map<String, Object> map = new HashMap<String,Object>();
//	    map.put("id", );
//	    map.put("pw", "movie@@33");
//	    redirectAtt.addFlashAttribute("id", user.getId()).addFlashAttribute("pw", "movie@@33");
	    
//		redirectAtt.addAttribute("id", user.getId());
//		redirectAtt.addAttribute("pw", "movie@@33");
		
		model.addAttribute("id", user.getId());
		model.addAttribute("pw", "movie@@33");
		
		return "/basic/naver_callback";
		
	}
	@RequestMapping("/main")
	public String mainpage(Model model) {
		System.out.println("main입장");

		// 박스오피스 순위 5개
		//System.out.println("현재 상영 영화 순위 1 ~ 5");
		
		model.addAttribute("titleShowing", movieInfoService.titleShowing());
		model.addAttribute("posterShowing", movieInfoService.posterShowing());
		model.addAttribute("codeShowing", movieInfoService.linkShowing());
		
		// 전체 게시판의 인기글 10개
		model.addAttribute("vogueList", ajaxBoardService.allVogueList());
		
		return "basic/main";
	}
	 @PostMapping("/upload")
	    public String upload() {
	       return "/basic/FileUpload";	
	 }
	 @RequestMapping(value="/idChk", method = RequestMethod.POST)
	 @ResponseBody
	 public int idChk(String id) throws Exception {
	 	int result = loginService.idChk(id);
	 	System.out.println(result);
	 	return result;
	 }
	 @RequestMapping(value="/nickChk", method = RequestMethod.POST)
	 @ResponseBody
	 public int nickChk(String nickname) throws Exception {
		 int result = loginService.nickChk(nickname);
	 	return result;
	 }
		//에러 출력 도우미 메소드
		public void showErrors(Errors errors) {
			if(errors.hasErrors()) {
				System.out.println("에러 개수: " + errors.getErrorCount());
				System.out.println("\t[field]\t|[code] ");
				List<FieldError> errList = errors.getFieldErrors();
					for(FieldError err : errList) {
						// binding 실패한 'field 이름' , binding 실패
						System.out.println("\t" + err.getField() + "\t|" + err.getCode());
					}
			} else {
				System.out.println("에러 없음");
			}
		}
		// 이 컨트롤러 클래스가 handler 에서 폼 데이터를 바인딩할때 검증하는 Validator를 결정해준다.
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.setValidator(new CustomerValidator());; 
		}


	 
}