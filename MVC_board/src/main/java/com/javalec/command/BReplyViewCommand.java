package com.javalec.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.dao.BDao;
import com.javalec.dto.BDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void excute(Model model) {

		Map<String , Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bId);
		
		model.addAttribute("reply_view", dto);
		
	}

}
