package com.gft.casadeeventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.casadeeventos.model.Casadeshow;
import com.gft.casadeeventos.repository.Casadeshows;

@Controller
@RequestMapping("/casadeshow")
public class Casadeshowcontroller {

	private static final String CASA_VIEW = "Casadeshow";

	@Autowired
	private Casadeshows casa;

	@RequestMapping
	public ModelAndView casadeshow() {
		List<Casadeshow> todasCasas = casa.findAll();
		ModelAndView mv = new ModelAndView(CASA_VIEW);
		mv.addObject(new Casadeshow());
		mv.addObject("casas", todasCasas);
		return mv;
	}

	@RequestMapping("/editar/{ID}")
	public ModelAndView editar(@PathVariable("ID") Casadeshow casaedit) {
		List<Casadeshow> todasCasas = casa.findAll();
		ModelAndView mv = new ModelAndView("Casadeshoweditar");
		mv.addObject(casaedit);
		mv.addObject("casas", todasCasas);
		return mv;
	}

	@RequestMapping(value = "{ID}")
	public String excluir(@PathVariable Long ID, RedirectAttributes attributes) {
		casa.deleteById(ID);
		attributes.addFlashAttribute("mensagem", "Casa excluída com sucesso!");
		return "redirect:/casadeshow";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Casadeshow casasShow, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "Casadeshow";
		}
		casa.save(casasShow);
		attributes.addFlashAttribute("mensagem", "Casa salva com sucesso!!");
		return "redirect:/casadeshow";
	}

}
