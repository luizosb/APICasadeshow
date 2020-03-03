package com.gft.casadeeventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.casadeeventos.model.Evento;
import com.gft.casadeeventos.repository.Eventos;

@Controller
@RequestMapping("/home")
public class Homecontroller {
	
	private static final String HOME_VIEW = "Home";
	
	@Autowired
	private Eventos event;
	
	@RequestMapping
	public ModelAndView home() {
		List <Evento> todosEventos = event.findAll();
		ModelAndView mv = new ModelAndView(HOME_VIEW);
		mv.addObject("eventos", todosEventos);
		return mv;
	}
	
	
	
}
