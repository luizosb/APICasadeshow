package com.gft.casadeeventos.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.casadeeventos.model.Casadeshow;
import com.gft.casadeeventos.model.Evento;
import com.gft.casadeeventos.model.Genero;
import com.gft.casadeeventos.repository.Casadeshows;
import com.gft.casadeeventos.repository.Eventos;

@Controller
@RequestMapping("/evento")
public class Eventocontroller {
	
	private static final String EVENTO_VIEW = "Evento";
	
	@Autowired
	private Eventos event;
	
	@Autowired
	private Casadeshows casa;
	
	@RequestMapping
	public ModelAndView evento() {
		List <Evento> todosEventos = event.findAll();
		ModelAndView mv = new ModelAndView(EVENTO_VIEW);
		mv.addObject(new Evento());
		mv.addObject("eventos", todosEventos);
		List <Casadeshow> todasCasas = casa.findAll();
		mv.addObject("casas", todasCasas);
		return mv;
	}
	
	@RequestMapping("/editar/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Evento eventoedit) {
		List <Evento> todosEventos = event.findAll();
		ModelAndView mv = new ModelAndView("Eventoseditar");
		mv.addObject("eventos", todosEventos);
		mv.addObject(eventoedit);
		List <Casadeshow> todasCasas = casa.findAll();
		mv.addObject("casas", todasCasas);
		return mv;
	}
	
	
	@RequestMapping(value ="{codigo}", method = RequestMethod.GET)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes){
		event.deleteById(codigo);
		attributes.addFlashAttribute("mensagem", "Evento excluído com sucesso!");
		return "redirect:/evento";
	}	
		
	@RequestMapping(method= RequestMethod.POST)
	public ModelAndView salvar(@Validated Evento evento, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(EVENTO_VIEW);
		if(errors.hasErrors()){
			List <Evento> todosEventos = event.findAll();
			mv.addObject("eventos", todosEventos);
			List <Casadeshow> todasCasas = casa.findAll();
			mv.addObject("casas", todasCasas);
			return mv;			
		}
		
		try {
		event.save(evento);
		List <Evento> todosEventos = event.findAll();
		mv.addObject(new Evento());
		mv.addObject("eventos", todosEventos);
		List <Casadeshow> todasCasas = casa.findAll();
		mv.addObject("casas", todasCasas);
		mv.addObject("mensagem", "Evento salvo com sucesso!!");
		return mv;		
		} catch (DataIntegrityViolationException e) {
			errors.rejectValue("data", null,"Formato de data inválido.");
			return mv;
		}
	}
	
	@ModelAttribute("todosGeneros")
	public List<Genero> todosGeneros(){
		return Arrays.asList(Genero.values());
	}
}
