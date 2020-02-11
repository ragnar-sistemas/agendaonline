package agendaonline.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendaonline.com.models.Medico;
import agendaonline.com.repositories.MedicoRepository;

@Controller
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@RequestMapping(value = "/medicos", method = RequestMethod.GET)
	public ModelAndView listaMedicos(Model model) {
		ModelAndView modelAndView = new ModelAndView("medico/listaMedicos");
		modelAndView.addObject("medicos", repository.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/medicos", method = RequestMethod.POST)
	public ModelAndView cadastroMedicos(Medico medico, BindingResult result, RedirectAttributes attributes) {
		ModelAndView modelAndView = new ModelAndView();
		repository.save(medico);
		modelAndView.setViewName("redirect:/medicos");
		return modelAndView;
	}

	@GetMapping("/medicos/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		repository.delete(id);
		modelAndView.setViewName("redirect:/medicos");
		return modelAndView;
	}

}
