package com.gft.casadeeventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.casadeeventos.model.Usuario;
import com.gft.casadeeventos.repository.Usuarios;

@Controller
public class CadastroController {

	private static final String CADASTRO_VIEW = "CadastroUsu";
	
	@Autowired
	private Usuarios usu;

	@RequestMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		return mv;
	}
	
	@PostMapping("/cadastro")
	public ModelAndView salvarUsuario(Usuario users) {
		ModelAndView mv = new ModelAndView("redirect:/login");
		usu.save(users);
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("Login");
		return mv;
	}
}
