package com.gft.casadeeventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.casadeeventos.model.Evento;
import com.gft.casadeeventos.model.Ingresso;
import com.gft.casadeeventos.repository.Eventos;
import com.gft.casadeeventos.repository.Ingressos;

@Controller
@RequestMapping("/compra")
public class Comprarcontroller {
	
	private static final String COMPRAR_VIEW = "HomeComprar";
	
	@Autowired
	private Eventos event;
	
	@Autowired
	private Ingressos ingrid;
	
	@RequestMapping(value ="/{codigo}", method= RequestMethod.GET)
	public ModelAndView tela(@PathVariable Long codigo) {
		Evento todosEventos = event.findById(codigo).get();
		ModelAndView mv = new ModelAndView(COMPRAR_VIEW);
		mv.addObject("eventos", todosEventos);
		mv.addObject(new Evento());
		return mv;
	}

	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public ModelAndView comprar(@PathVariable("codigo") Long codigo, RedirectAttributes atributes, int qtdIngresso) {
		Evento todosEventos = event.findById(codigo).get();
		ModelAndView mv = new ModelAndView("redirect:/historico");
		if (todosEventos.getCapacidade() > 0 && todosEventos.getCapacidade()-qtdIngresso >= 0) {
		todosEventos.setCapacidade(todosEventos.getCapacidade() - qtdIngresso);
		Ingresso ingr = new Ingresso(todosEventos.getCodigo(),todosEventos.getNome(), todosEventos.getPreco(),todosEventos.getData(),  qtdIngresso);
		ingrid.save(ingr);
		mv.addObject(todosEventos.getCapacidade());
		event.save(todosEventos);
		}
		else 
		{
			
		}
		return mv;
	}
	
	
}
