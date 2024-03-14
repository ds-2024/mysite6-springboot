package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {
	
	//리스트
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody  //return의 데이타를 json방식으로변경해서 응답문서(response)의 바디(body)에 붙여서 보내줘
	@RequestMapping(value="/api/guestbooks", method = RequestMethod.GET)
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");

		List<GuestbookVo> guestbookList =guestbookService.exeAddList();
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	//등록(Post) //낱개로 받을거면 param 2개이상이면 modelAttribute
	@ResponseBody // 이 어노테이션이 있어야 jason이 받아서 ok 사인을 콘솔에 보여줌.
	@RequestMapping(value= "/api/guestbooks", method = RequestMethod.POST) 
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add()");
		System.out.println(guestbookVo);
		
		GuestbookVo gVo = guestbookService.exeAddandGuest(guestbookVo);
		System.out.println(gVo);
		return gVo; //로드됐을때 전체가져오는 코드가 있어서 F5새로고침하면 등록 누른 정보 나옴.
		
		//그러면 어떻게 해야 방금 저장된 정보를 가져올 수 있을까?
		//-->sol) MAX no 를 가져오라고 한다.조회하는 사이에 MAX값이 바뀔 수 있음(유저 많을때)	
	}
	
	
}
