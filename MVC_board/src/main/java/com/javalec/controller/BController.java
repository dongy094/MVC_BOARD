package com.javalec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.command.BCommand;
import com.javalec.command.BContentCommand;
import com.javalec.command.BDeleteCommand;
import com.javalec.command.BListCommand;
import com.javalec.command.BModifyCommand;
import com.javalec.command.BReplyCommand;
import com.javalec.command.BReplyViewCommand;
import com.javalec.command.BWriteCommand;
import com.javalec.template.Constant;


@Controller
public class BController {
	
BCommand command;

public JdbcTemplate template;

@Autowired
public void setTemplate(JdbcTemplate template) {
	this.template = template;
	Constant.template = this.template;
}



	@RequestMapping("/list")	//리스트 보기
	public String list(Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		command.excute(model);
		return "list";  //list.jsp로 이동
	}
	
	@RequestMapping("/write_view")    //작성하는 화면으로 이동
	public String write_view(Model model) {
		System.out.println("write_view()");
		return "write_view";
	}
	
	@RequestMapping("/write") //작성하기,   request를 통해서 작성한 정보들 넘어오는거  받기
	public String write(HttpServletRequest request,Model model) {
		System.out.println("write()");
		model.addAttribute("request", request); 	// String name = rqeust.getparamater 이렇게 해서 각 값을 변수에 저장하고
													// 그 값을 함수 실행시 넘겨주는게 아니라 아예 모델에 request를 탑제	
		
		command = new BWriteCommand();
		command.excute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/content_view") //게시글 내용보기
	public String content_view(HttpServletRequest request,Model model) {		//데이터를 받아와서 보여줘야 하니까 데이터를 받아줄것이 필요
		System.out.println("content_view()");
		
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.excute(model);
		return "content_view";
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value="/modify") 
	public String modify(HttpServletRequest request,Model model) {		
		System.out.println("modify()");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.excute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view") //해당글의 답변하는 페이지로 이동
	public String reply_view(HttpServletRequest request,Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.excute(model);
		return "reply_view";
	}
	
	@RequestMapping("/reply") // 답변 작성한것 넣기
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.excute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.excute(model);
		
		
		return "redirect:list";
	}
	

}
