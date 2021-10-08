package com.spring.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class BoardFileUploadController {
	
    @RequestMapping("/ckUpload")
    @ResponseBody
    public String imageUpload(HttpServletRequest request,
            			HttpServletResponse response,
            			MultipartFile upload) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
 
        // 랜덤문자생성
        UUID uid = UUID.randomUUID();
        
        //업로드한 파일 이름
        String fileName = uid + upload.getOriginalFilename();
 
        //파일을 바이트 배열로 변환
        byte[] bytes = upload.getBytes();
 
        //이미지를 업로드할 디렉토리(배포 디렉토리로 설정)
        ServletContext context = request.getServletContext();
     	String saveDirectory = context.getRealPath("/file/board/");
     	// 실제 저장되는 물리적인 경로 확인하기
        System.out.println(saveDirectory+fileName);
        OutputStream out=new FileOutputStream(new File(saveDirectory+fileName));
 
        //서버로 업로드
        out.write(bytes);
        
        String fileUrl = "/file/board/" + fileName;

		// CKEditor 측에 보내줄 response (JSON)
		String jsonString = "{\r\n"
				+ "    \"uploaded\": 1,\r\n"
				+ "    \"fileName\": \"" + fileName + "\",\r\n"
				+ "    \"url\": \"" + fileUrl + "\"\r\n"
				+ "}";
		
		response.setContentType("application/json; charset=utf-8");  // MIME 설정
		return jsonString;
    }
}
