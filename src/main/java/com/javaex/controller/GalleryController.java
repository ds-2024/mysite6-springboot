package com.javaex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;



@Controller
public class GalleryController {
	
	@Autowired 
	private GalleryService galleryService;
	
	//갤러리리스트
	@RequestMapping(value="/gallery/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String uploadList() {
		System.out.println("GalleryController.list()");
		
		
		return "gallery/list";
	}
	
	//이미지 파일 업로드(등록)
	@RequestMapping(value = "/gallery/upload", method = RequestMethod.POST) //value 값에 공백 있어도 오류 뜬다.. 공백오류 조심..
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
		System.out.println("GalleryController.upload()");//오류난 이유) 바인딩 시켜줄 html name 값이 공란이라서 <input id="file" type="file" name="file" value="">
		
		
		System.out.println(file.getOriginalFilename());
		
		String saveName = galleryService.exeupload(file);
		model.addAttribute("saveName", saveName);
		
		System.out.println(saveName);
		return "gallery/list";
	}

}
