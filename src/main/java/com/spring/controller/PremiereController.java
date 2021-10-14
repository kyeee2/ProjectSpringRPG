package com.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.PremiereDTO;
import com.spring.service.AjaxPremiereService;


@Controller
public class PremiereController{
	
	@Autowired
	AjaxPremiereService premiereService;
	
	//@RequestMapping(value = "/premiere/list", method=RequestMethod.GET)
	@RequestMapping({"premiere/list", "/admin/premiere/list"})
	public String premiereList(Model model) {
		return "/admin/premiere/info/list";
	}
	
	
	@RequestMapping("/admin/premiere/write")
	public String premiereWrite() {
		return "/admin/premiere/info/write";
	}
	
	@PostMapping("/admin/premiere/writeOk")
	public String premiereWriteOk(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("file") MultipartFile file,
			PremiereDTO dto, 
			Model model) {
		
		System.out.println(dto.toString());
		try {
		    // 진짜 파일 이름
		    String filename = file.getOriginalFilename();
		    
		    if(filename.equals("")) {
		    	// 파일을 안올렸다면
		    	System.out.println("파일 안올림");
		    	
			    dto.setPhoto("");
			    dto.setPhoto_origin("");
			    int cont = premiereService.write(dto);

				return "/admin/premiere/info/writeOk";
		    }
			// 파일을 올렸다면
		    
	        // 랜덤문자생성
	        UUID uid = UUID.randomUUID();
	        
	        //업로드된 파일 이름
	        String cfilename = uid + "_" + filename;
	 
	        //이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
	        ServletContext context = request.getServletContext();
	     	String saveDirectory = context.getRealPath("/file/premiere");	// 무조건 여기 폴더에 저장
		    
		    File uploadFile = new File(saveDirectory);
		    // 디렉터리가 존재하지 않을 경우
            if(!uploadFile.exists()) {
                boolean wasSuccessful = uploadFile.mkdirs();
  
	            // 디렉터리 생성에 실패했을 경우
	            if(!wasSuccessful)
	                System.out.println("file: was not successful");
            }
            
		    dto.setPhoto(cfilename);
		    dto.setPhoto_origin(filename);
		    // DB에 데이터 저장하기
		    int result = premiereService.write(dto);
		    model.addAttribute("result", result);
		    // 성공적으로 DB에 들어갔다면
		    if(result != 0) {
		     	String filepath = Paths.get(saveDirectory, cfilename).toString();
		     	
	            // premiere 폴더에 파일 업로드
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
	            stream.write(file.getBytes());
	            stream.close();
		    }
            
          }
          catch (Exception e) {
            System.out.println(e.getMessage());
          }

		return "/admin/premiere/info/writeOk";
	}
	
	@RequestMapping({"premiere/view", "/admin/premiere/view"})
	public String premiereView(int uid, Model model) {
		return "/admin/premiere/info/view";
	}
	
	@RequestMapping("/admin/premiere/update")
	public String premiereUpdate(int uid, Model model) {
		return "/admin/premiere/info/update";
	}
	
	@PostMapping("/admin/premiere/updateOk")
	public String premiereUpdateOk(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam("file") MultipartFile file,
			PremiereDTO dto, 
			Model model) {
		
		System.out.println("file: " + dto.getPhoto());
		
		int result = 0;

		try {
			// 진짜 파일 이름
			String filename = file.getOriginalFilename();
			String cfilename = "";

			if (filename.equals("")) {
				// 파일을 안올렸다면
				System.out.println("파일 안올림");

				dto.setPhoto("");
				dto.setPhoto_origin("");
				result = premiereService.update(dto);
			} else {
				// 파일을 올렸다면

				// 랜덤문자생성
				UUID uid = UUID.randomUUID();

				// 업로드된 파일 이름
				cfilename = uid + "_" + filename;

				// 이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
				ServletContext context = request.getServletContext();
				String saveDirectory = context.getRealPath("/file/premiere"); // 무조건 여기 폴더에 저장

				File uploadFile = new File(saveDirectory);
				// 디렉터리가 존재하지 않을 경우
				if (!uploadFile.exists()) {
					boolean wasSuccessful = uploadFile.mkdirs();

					// 디렉터리 생성에 실패했을 경우
					if (!wasSuccessful)
						System.out.println("file: was not successful");
				}

				String oldFilename = dto.getPhoto();
				
				dto.setPhoto(cfilename);
				dto.setPhoto_origin(filename);
				// DB에 데이터 저장하기
				result = premiereService.update(dto);

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

		return "/admin/premiere/info/updateOk";
	}
	
	@PostMapping("premiere/deleteOk")
	public String premiereDeleteOk(HttpServletRequest request,
			HttpServletResponse response, 
			int [] uid, 
			Model model) {
		
		if(uid.length == 0) {
			// 아무것도 삭제하지 않는다면 다시 목록으로
			return "/admin/premiere/info/list";
		}
		
		List<String> filenames = premiereService.getFileName(uid);
		
		int result = premiereService.deleteByUid(uid);

		// 이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
		ServletContext context = request.getServletContext();
		String saveDirectory = context.getRealPath("/file/premiere"); // 무조건 여기 폴더에 저장
		
		for(int i = 0; i < filenames.size(); i++) {
			System.out.println(filenames.get(i));
			// 원래 파일 삭제하기
			File oldFile = new File(saveDirectory + File.separator + filenames.get(i));
			System.out.println(oldFile.getPath());
			oldFile.delete();
		}
		
		return "/admin/premiere/info/deleteOk";
	}
	
	// 시사회 당첨 추첨
	@RequestMapping("/admin/premiere/win")
	public String premiereWin(Model model) {
		model.addAttribute("titleList", premiereService.getTitle());
		return "/admin/premiere/info/win";
	}
	
	// 시사회 당첨 추첨 완료 클릭하면 
	@PostMapping("/admin/premiere/winOk")
	public String premiereWinOk(int prUid, int count, String [] email, String [] nickname, Model model) {
		System.out.println("prUid : " + prUid);
		System.out.println("count: " + count);
		System.out.println("email : ");
		String email_arr = "";
		for(int i = 0; i < email.length; i++) {
			if(i != 0) {
				email_arr += ", ";
			}
			email_arr += "'";
			email_arr += email[i];
			email_arr += "'";
		}
		String nicknames = "";
		for(int i = 0; i < nickname.length; i++) {
			nicknames += nickname[i] + "<br>";
		}
		System.out.println(email_arr);
		System.out.println(nicknames);
		model.addAttribute("updateBool", premiereService.updateBool(prUid, email_arr, nicknames));
		return "/admin/premiere/info/winOk";
	}
	
	// 시사회 당첨자 발표 목록 페이지
	@RequestMapping({"/premiereWin", "/admin/premiereWin"})
	public String premiereWinList(Model model) {
		return "/admin/premiere/win/list";
	}
	
	// 시사회 당첨자 발표 조회
	@RequestMapping({"/premiereWin/view", "/admin/premiereWin/view"})
	public String premiereWinView() {
		return "/admin/premiere/win/view";
	}
	
	// 시사회 당첨자 발표 삭제
	@RequestMapping("/admin/premiereWin/deleteOk")
	public String premiereWinDeleteOk() {
		return "/admin/premiere/win/deleteOk";
	}
	
	
}
















