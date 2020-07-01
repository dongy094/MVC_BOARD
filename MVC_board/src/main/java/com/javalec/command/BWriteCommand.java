package com.javalec.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.javalec.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void excute(Model model) {
			
			Map<String , Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");
			String bName = request.getParameter("bName");
			String bTitle = request.getParameter("bTitle");
			String bContent = request.getParameter("bContent");
			
			BDao dao = new BDao();  //작성한글 넣어주기만 하면 되니까 뭐 다시 받아올거 없음
			dao.write(bName,bTitle,bContent);
		
	}

}
