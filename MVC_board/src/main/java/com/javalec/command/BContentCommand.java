package com.javalec.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.dao.BDao;
import com.javalec.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void excute(Model model) {
		BDao dao = new BDao();

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String bId = request.getParameter("bId");
		BDto dto = dao.contentView(bId);
		
		model.addAttribute("content_view",dto);
		
	}

}
