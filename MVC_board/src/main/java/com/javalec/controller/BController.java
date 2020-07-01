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



	@RequestMapping("/list")	//����Ʈ ����
	public String list(Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		command.excute(model);
		return "list";  //list.jsp�� �̵�
	}
	
	@RequestMapping("/write_view")    //�ۼ��ϴ� ȭ������ �̵�
	public String write_view(Model model) {
		System.out.println("write_view()");
		return "write_view";
	}
	
	@RequestMapping("/write") //�ۼ��ϱ�,   request�� ���ؼ� �ۼ��� ������ �Ѿ���°�  �ޱ�
	public String write(HttpServletRequest request,Model model) {
		System.out.println("write()");
		model.addAttribute("request", request); 	// String name = rqeust.getparamater �̷��� �ؼ� �� ���� ������ �����ϰ�
													// �� ���� �Լ� ����� �Ѱ��ִ°� �ƴ϶� �ƿ� �𵨿� request�� ž��	
		
		command = new BWriteCommand();
		command.excute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/content_view") //�Խñ� ���뺸��
	public String content_view(HttpServletRequest request,Model model) {		//�����͸� �޾ƿͼ� ������� �ϴϱ� �����͸� �޾��ٰ��� �ʿ�
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
	
	@RequestMapping("/reply_view") //�ش���� �亯�ϴ� �������� �̵�
	public String reply_view(HttpServletRequest request,Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.excute(model);
		return "reply_view";
	}
	
	@RequestMapping("/reply") // �亯 �ۼ��Ѱ� �ֱ�
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
