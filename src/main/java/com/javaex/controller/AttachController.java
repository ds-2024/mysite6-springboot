package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;

@Controller
public class AttachController {
	
	@Autowired
	private AttachService attachService;
	
	@RequestMapping(value = "/attach/uploadform", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadform() {
		System.out.println("AttachController.uploadform()");
		
		return "attach/form";
	}
	
	@RequestMapping(value = "/attach/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
		System.out.println("AttachController.upload()");
		
		System.out.println(file.getOriginalFilename());
		//System.out.println(file.toString());잘못 올려도 실제 본체가 없어도 올라옴 따라서 toString은 안씀
		
		String saveName = attachService.exeupload(file);//Service 작업 끝나고 돌아와서 saveName에 저장
		model.addAttribute("saveName", saveName);//*공백 처리 주의. 왜냐면 .jsp 축약되거니까. + 컨트롤러에서 생성된 데이터를 뷰로 전달하여 화면에 표시하는데 사용되는 명령문 
		
		return "attach/result";
	}
}
