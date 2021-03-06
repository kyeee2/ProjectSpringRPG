	package com.spring.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.multipart.MultipartFile;

import com.spring.CustomerValidator;
import com.spring.config.PrincipalDetails;
import com.spring.domain.CustomerDTO;
import com.spring.service.AjaxBoardService;
import com.spring.service.AjaxPremiereService;
import com.spring.service.LoginService;
import com.spring.service.MovieCrawlingService;

@Controller

public class LoginController {

	public LoginController() {
		System.out.println("LoginController() ??????");
	}

	@Autowired
	LoginService loginService;
	
	// ??????????????????
	MovieCrawlingService movieInfoService;
	AjaxBoardService ajaxBoardService;
	AjaxPremiereService ajaxPremiereService;
	
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

	


	@Autowired
	public void setAjaxPremiereService(AjaxPremiereService ajaxPremiereService) {
		this.ajaxPremiereService = ajaxPremiereService;
	}

	public void naverlogin() {	}
	//????????? ?????????
	@RequestMapping("/login")
	public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {	
		// ????????? ?????????
		String clientId = "MdDnprhEU_V4J7WsPkRu";//?????????????????? ??????????????? ????????????";
	    String redirectURI = URLEncoder.encode("http://localhost:8090/callback", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    session.setAttribute("state", state);
	    model.addAttribute("apiURL", apiURL);
	return "/basic/login";	
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
        
        System.out.println("????????? ?????? : " + phonenum);
        System.out.println("???????????? : " + numStr);
        loginService.certifiedPhoneNumber(phonenum,numStr);
        return numStr;
  
}	
		
	@PostMapping("/joinOk")
	public String joinOk(HttpServletRequest request, 
			HttpServletResponse response,
			@ModelAttribute @Valid CustomerDTO user,
			@RequestParam("file") MultipartFile file,
			BindingResult bindresult, 
			Model model) throws Exception {
//		String fileUrl = FileHelper.upload("/uploads", file, request);
//		user.setProfile(fileUrl);
//		joinOk(user);
		
		String rawPassword = user.getPw();
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPw(encPassword);
		
		if(bindresult.hasErrors()) {   // ?????? ?????????
			return "/basic/join";  // ?????? ????????? ????????????
		}
		
		String id = user.getId();
		int checkid = loginService.idChk(id);
		String nickname = user.getNickname();
		int checknick = loginService.nickChk(nickname);
		
		int result = 0;
		
		try {
		    // ?????? ?????? ??????
		    String filename = file.getOriginalFilename();
		    
		    if(filename.equals("")) {
		    	// ????????? ???????????????
		    	System.out.println("?????? ?????????");
		    	
			    user.setProfile("");
			    user.setProfile_origin("");
				try {
					if(checkid == 1 || checknick == 1) {
						return "/basic/join";
					}else if(checkid == 0 || checknick == 0) {
						result = loginService.addMember(user);
					}
					// ????????????~ ????????? ???????????? ??????????????? -> ?????? ???????????? ???????????? ???????????? 
					// ???????????? ???????????? -> register
				} catch (Exception e) {
					throw new RuntimeException();
				}

				return "redirect:/login";
		    }
			// ????????? ????????????
		    
	        // ??????????????????
	        UUID uid = UUID.randomUUID();
	        
	        //???????????? ?????? ??????
	        String cfilename = uid + "_" + filename;
	 
	        //???????????? ???????????? ????????????(?????? ??????????????? ??????)
	        ServletContext context = request.getServletContext();
	     	String saveDirectory = context.getRealPath("/file/customer");	// ????????? ?????? ????????? ??????
		    
		    File uploadFile = new File(saveDirectory);
		    // ??????????????? ???????????? ?????? ??????
            if(!uploadFile.exists()) {
                boolean wasSuccessful = uploadFile.mkdirs();
  
	            // ???????????? ????????? ???????????? ??????
	            if(!wasSuccessful)
	                System.out.println("file: was not successful");
            }
            
		    user.setProfile(cfilename);
		    user.setProfile_origin(filename);
		    // DB??? ????????? ????????????

			try {
				if(checkid == 1 || checknick == 1) {
					return "/basic/join";
				}else if(checkid == 0 || checknick == 0) {
					result = loginService.addMember(user);
				}
				// ????????????~ ????????? ???????????? ??????????????? -> ?????? ???????????? ???????????? ???????????? 
				// ???????????? ???????????? -> register
			} catch (Exception e) {
				throw new RuntimeException();
			}
			
		    // ??????????????? DB??? ???????????????
		    if(result != 0) {
		     	String filepath = Paths.get(saveDirectory, cfilename).toString();
		     	
	            // premiere ????????? ?????? ?????????
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	            stream.write(file.getBytes());
	            stream.close();
		    }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		model.addAttribute("result", result);
		
		return "redirect:/login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "/basic/logout";
	}
	@RequestMapping("/callback")
	public String naver_callback(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		//, RedirectAttributes redirectAtt
	    String clientId = "MdDnprhEU_V4J7WsPkRu";//?????????????????? ??????????????? ????????????";
	    String clientSecret = "a1VUtxhLxH";//?????????????????? ??????????????? ????????????";
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
	      con.setRequestMethod("POST");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode);
	      if(responseCode==200) { // ?????? ??????
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // ?????? ??????
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
		if(access_token != null) { // access_token??? ??? ???????????????
			try {
				String apiurl = "https://openapi.naver.com/v1/nid/me";
				URL url = new URL(apiurl);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization", header);
				
				int responseCode = con.getResponseCode();
				BufferedReader br;
				
				if(responseCode==200) { // ?????? ??????
				 br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {  // ?????? ??????
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
				
				System.out.println("???????????? ?????? ??? ????????????? : " + resObj);
				
				//?????? ?????? ????????? ????????? ?????? ????????? ??????. 
				//???, ????????? get()?????? ???????????? ?????? ????????? developer ??????????????? ????????? ???????????? ???????????????.
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
				
				String encPassword = passwordEncoder.encode("movie@@33"); //DB?????? Not null??? ??????????????? ????????? ??? ???
				user.setPw(encPassword);
				
				user.setName((String) resObj.get("name"));
				user.setNickname((String) resObj.get("nickname"));
				user.setBirthday(Integer.parseInt(birthday));
				String mobile = (String)resObj.get("mobile");
				mobile = mobile.replace("-", "");
				user.setPhonenum(mobile);
				user.setEnable(1);
			        /*member.setBirth((String) response.get("birthday"));*/
				
				System.out.println("custor ?????? : " + user.toString());
				
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		
		System.out.println("controller : " + user.getId());
		if(user.getId() != null) {
			loginService.addMember(user);	// ???????????? ????????? DB??? ??????
		}
		
//		Map<String, Object> map = new HashMap<String,Object>();
//	    map.put("id", );
//	    map.put("pw", "movie@@33");
//	    redirectAtt.addFlashAttribute("id", user.getId()).addFlashAttribute("pw", "movie@@33");
	    
//		redirectAtt.addAttribute("id", user.getId());
//		redirectAtt.addAttribute("pw", "movie@@33");
		
		model.addAttribute("id", user.getId());
		model.addAttribute("pw", "movie@@33");
		
		UsernamePasswordAuthenticationToken authReq
	      = new UsernamePasswordAuthenticationToken(user.getId(), "movie@@33");
	    SecurityContextHolder.getContext().setAuthentication(authReq);
	    
		return "redirect:/main";
		
	}
	@RequestMapping("/main")
	public String mainpage(Model model) {
		System.out.println("main??????");

		// ??????????????? ?????? 5???
		//System.out.println("?????? ?????? ?????? ?????? 1 ~ 5");
		
		model.addAttribute("titleShowing", movieInfoService.titleShowing());
		model.addAttribute("posterShowing", movieInfoService.posterShowing());
		model.addAttribute("codeShowing", movieInfoService.linkShowing());
		
		// ?????? ???????????? ????????? 10???
		model.addAttribute("vogueList", ajaxBoardService.allVogueList());
		
		// ????????? ?????? 3???
		model.addAttribute("premiereList", ajaxPremiereService.getThreeRecently());
		
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
	 @GetMapping("/findIDPW")
	 public String findIDPW() {
		
	       
		 return "/basic/findIDPW";
	 }
	 
	 @PostMapping("/findIDOk")
	 public String findID( String name, String phonenum, Model model) throws Exception {	       
		model.addAttribute("id",loginService.findID(name, phonenum));
		 return "/basic/findIDOk";
	 }
	 @PostMapping("/changePw")// ???????????? select id name phonenum ???
	 public String changePw(String id, String name, String phonenum, Model model){
		 model.addAttribute("id", id);
		 model.addAttribute("pw",loginService.selectPw(id, name, phonenum));
		 return "/basic/changePw";
	 }
	 @PostMapping("/findPWOk")// pw??? ????????? update
	 public String findPW(String pw, String id, Model model) throws Exception {
	       
		 int result = 0;
		 if(pw!= null) {
	        String rawPassword = pw;
			String encPassword = passwordEncoder.encode(rawPassword);
			pw= encPassword;
			result = loginService.setPw(pw, id);
			}
		 model.addAttribute("result", result);
		 return "/basic/findPWOk";
	 }
		//?????? ?????? ????????? ?????????
		public void showErrors(Errors errors) {
			if(errors.hasErrors()) {
				System.out.println("?????? ??????: " + errors.getErrorCount());
				System.out.println("\t[field]\t|[code] ");
				List<FieldError> errList = errors.getFieldErrors();
					for(FieldError err : errList) {
						// binding ????????? 'field ??????' , binding ??????
						System.out.println("\t" + err.getField() + "\t|" + err.getCode());
					}
			} else {
				System.out.println("?????? ??????");
			}
		}
		// ??? ???????????? ???????????? handler ?????? ??? ???????????? ??????????????? ???????????? Validator??? ???????????????.
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.setValidator(new CustomerValidator(loginService));
		}


	 
}