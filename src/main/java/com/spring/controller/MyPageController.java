package com.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.config.PrincipalDetails;
import com.spring.domain.CustomerDTO;
import com.spring.service.LoginService;

@Controller
@RequestMapping("/user")
public class MyPageController {
	
	public MyPageController() {
		System.out.println("MyPageController() 생성");
	}
	@Autowired
	LoginService loginService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 마이페이지 컨트롤러
	@RequestMapping("/myInfo")
	public String myInfo(Authentication authentication, Model model) {

		// 로그인 정보에서 고유번호 가져오기
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        int uid = userDetails.getUid();
       
        model.addAttribute("list", loginService.selectByUid(uid));
		
		
		return "/myPage/info/view";
	}
	@RequestMapping("/myInfo/update")
	public String update(Authentication authentication, Model model) throws Exception{
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        int uid = userDetails.getUid();
		
		model.addAttribute("list", loginService.selectByUid(uid));
		return "/myPage/info/update";
	}
	
	@PostMapping("/myInfo/updateOk")
	public String updateOk(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("file") MultipartFile file,
			CustomerDTO dto, 
			Authentication authentication, 
			Model model) throws Exception{
		// 현재 로그인 된 회원의 고유번호 DTO에 세팅해주기
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        CustomerDTO user = userDetails.getUser();
		System.out.println(user);
		dto.setUid(user.getUid());
		
		int result = 0;

		try {
			// 진짜 파일 이름
			String filename = file.getOriginalFilename();
			String cfilename = "";

			if (filename.equals("")) {
				if(dto.getProfile() == null) {
					// 파일을 안올렸다면
					System.out.println("파일 안올림");
	
					dto.setProfile("");
					dto.setProfile_origin("");
					result = loginService.update(dto);
				} else {
					// 파일은 변경하지 않았다면 title과 content만 수정
					result = loginService.updateNoFile(dto);
				}
			} else {
				// 파일을 올렸다면

				// 랜덤문자생성
				UUID uid = UUID.randomUUID();

				// 업로드된 파일 이름
				cfilename = uid + "_" + filename;

				// 이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
				ServletContext context = request.getServletContext();
				String saveDirectory = context.getRealPath("/file/customer"); // 무조건 여기 폴더에 저장

				File uploadFile = new File(saveDirectory);
				// 디렉터리가 존재하지 않을 경우
				if (!uploadFile.exists()) {
					boolean wasSuccessful = uploadFile.mkdirs();

					// 디렉터리 생성에 실패했을 경우
					if (!wasSuccessful)
						System.out.println("file: was not successful");
				}

				String oldFilename = dto.getProfile();
				
				dto.setProfile(cfilename);
				dto.setProfile_origin(filename);
				// DB에 데이터 저장하기
				result = loginService.update(dto);

				// 성공적으로 DB에 들어갔다면
				if (result != 0) {
					// 원래 파일 삭제하기
					File oldFile = new File(saveDirectory + File.separator + oldFilename);
					System.out.println(oldFile.getPath());
					oldFile.delete();
					
					// 새 파일 업로드 하기
					String filepath = Paths.get(saveDirectory, cfilename).toString();

					// premiere 폴더에 파일 업로드
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
					stream.write(file.getBytes());
					stream.close();
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		model.addAttribute("result", result);

		return "/myPage/info/updateOk";
	}

	@GetMapping("/myInfo/deleteOk")
	public String deleteOk(Authentication authentication, Model model) {
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		CustomerDTO user= userDetails.getUser();
		String id = user.getId();
		System.out.println("탈퇴아이디: " + id);
		model.addAttribute("result", loginService.deleteMember(id));
		//userDetails.isEnabled();
		return "/myPage/info/deleteOk";
	}
	
	@GetMapping("/myInfo/pwUpdate")
	public String pwUpdate( Authentication authentication, Model model) {
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		int uid = userDetails.getUid();
		model.addAttribute("list", loginService.selectByUid(uid));
		return "/myPage/info/pwUpdate";
	}
	
	@PostMapping("/myInfo/pwUpdateOk")
	public String pwUpdateOk(String pw, Authentication authentication, Model model) throws Exception{
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        CustomerDTO user= userDetails.getUser();
        int uid = user.getUid();
        
        String rawPassword = pw;
        System.out.println(rawPassword);
		String encPassword = passwordEncoder.encode(rawPassword);
		user.setPw(encPassword);
		pw= user.getPw();
        model.addAttribute("result", loginService.updatePw(pw, uid));
		return "/myPage/info/pwUpdateOk";
	}
	// 마이페이지 게시글/ 댓글 조회 페이지
	@RequestMapping("/myPost")
	public String myPost() {	// 특정 회원 uid
		return "/myPage/post/list";
	}
}
