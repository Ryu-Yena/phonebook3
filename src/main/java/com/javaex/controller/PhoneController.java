package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	//피드
	//생성자
	//메소드 g/s
	
	/*메소드마다 기능 1개씩 --> 기능마다 url 부여 (action 불필요) */

	
	// 리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("리스트");
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());
		
		//model --> data 를 보내는 방법 --> 담아 놓으면 된다.
		model.addAttribute("pList", personList);
		
		return "/WEB-INF/views/list.jsp";
	}
	
	
	
	// 등록폼
	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("등록폼");
		
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	//등록
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		
		
		System.out.println("등록");
		System.out.println(name  + ", " + hp + ", " + company);
		
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo.toString());
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);
		
		return "redirect:/phone/list";
	}


	// 수정폼 --> modifyForm
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		
		System.out.println("수정폼");
		
		return "/WEB-INF/views/modifyForm.jsp";
		
		
	}

	// 수정 --> modify
	@RequestMapping(value="/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company,
						 @RequestParam("no") int no) {
		
		System.out.println("수정");
		System.out.println(name + ", " + hp + ", " + company + ", " + no);
		
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo.toString());
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
	}

	// 삭제 --> delete
	
	public String delete(@RequestParam("no") int no) {
		
		System.out.println("삭제");
		
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personDelete(no);
		
		return "redirect:/phone/list";
	}
	
	

}
