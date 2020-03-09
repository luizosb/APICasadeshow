package com.gft.casadeeventos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.casadeeventos.model.Ingresso;
import com.gft.casadeeventos.repository.Ingressos;
import com.gft.casadeeventos.services.CompraService;

@Controller
@RequestMapping("/historico")
public class Historicocontroller {
	
	private static final String HIST_VIEW = "Historico";
	
	@Autowired
	private CompraService compraServ;
	
	@Autowired
	private Ingressos ingress;
	
	@RequestMapping
	public ModelAndView historico() {
		ModelAndView mv = new ModelAndView(HIST_VIEW);
		mv.addObject(new Ingresso());
		mv.addObject("ingressos", compraServ.buscarHistorico());
		return mv;
		
	}
	
	@RequestMapping(value = "{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ingress.deleteById(id);
		attributes.addFlashAttribute("mensagem", "Compra excluída com sucesso!");
		return "redirect:/historico";
	}
	
}
