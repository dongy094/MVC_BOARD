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
			
			BDao dao = new BDao();  //�ۼ��ѱ� �־��ֱ⸸ �ϸ� �Ǵϱ� �� �ٽ� �޾ƿð� ����
			dao.write(bName,bTitle,bContent);
		
	}

}
