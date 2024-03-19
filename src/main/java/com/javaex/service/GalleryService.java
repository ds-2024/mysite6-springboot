package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.TboardVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;

	public String exeupload(MultipartFile file) {
		System.out.println("GalleryService.exeupload()");
		System.out.println(file.getOriginalFilename());

		// 파일저장 폴더
		String saveDir = "C:\\javaStudy\\listupload"; // cf 문자열표시 \\

		// (0)파일관련 정보수집
		// 오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName:" + orgName);

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf(".")); // .이후로 나타나는 위치를 반환.
		System.out.println("exName" + exName);
		System.out.println(orgName.lastIndexOf("."));

		// 저장 파일명(겹치지 않아야 한다) 그래서 버튼누른시간정보가 들어감.
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName: " + saveName);

		// 파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: " + fileSize);

		// 파일 전체 경로(저장파일명 포함)
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);

		// (1)파일정보를 DB에 저장
		// *vo묶어주고
		GalleryVo galleryVo = new GalleryVo(filePath, orgName, saveName, fileSize);
		System.out.println(galleryVo);

		// *db에 저장
		
		System.out.println("............DB저장완료");
		// dao의 메소드 호출해서 저장 --> 만들어 볼것
		//List<GalleryVo> galleryList = GalleryDao.gallerySelectList();

		// (2)파일을 하드디스크에 저장
		// *파일저장
		try {

			byte[] fileData = file.getBytes();

			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);

			bos.write(fileData);
			bos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return saveName;

	}

}
