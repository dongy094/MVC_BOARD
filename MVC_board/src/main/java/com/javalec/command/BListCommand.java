package com.javalec.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.dao.BDao;
import com.javalec.dto.BDto;


public class BListCommand implements BCommand {

	@Override
	public void excute(Model model) {

		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);

	}

}
