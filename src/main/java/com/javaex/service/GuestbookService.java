package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	// 등록폼+리스트
	public List<GuestbookVo> exeAddList() {
		System.out.println("GuestbookService.exeAddList()");

		List<GuestbookVo> guestbookList = guestbookDao.guestbookSelectList();

		return guestbookList;
	}

	// 등록
	public int exeAdd(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeAdd()");

		int count = guestbookDao.guestbookInsert(guestbookVo);
		return count;
	}

	// 삭제
	public int exeRemove(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeRemove()");

		int count = guestbookDao.guestbookDelete(guestbookVo);
		return count;
	}

	
	//ajax 방명록 저장
	public GuestbookVo exeAddandGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestService.exeAddandGuest()");
		System.out.println(guestbookVo);
	    
		//저장
		System.out.println("전"+guestbookVo);//no 없음
		guestbookDao.insertSelectkey(guestbookVo);
		System.out.println("후"+guestbookVo);//no 있음
		
		//1명 데이터 가녀오기 
		GuestbookVo gVo= guestbookDao.guestbookSelectOne(guestbookVo.getNo());
		return gVo;
	  
		
	}
}
