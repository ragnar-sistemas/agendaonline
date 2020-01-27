package agendaonline.com.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendaonline.com.models.Convenio;
import agendaonline.com.repositories.ConvenioRepository;

@Controller
public class ConveniosController {

	@Autowired
	private ConvenioRepository repository;

	@RequestMapping("/convenios")
	public ModelAndView listaConvenios() {
		ModelAndView modelAndView = new ModelAndView("convenio/listaConvenios");
		modelAndView.addObject("convenios", repository.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/convenios", method = RequestMethod.POST)
	public ModelAndView cadastroConvenio(@Valid Convenio convenio, BindingResult result, RedirectAttributes attributes) {
		ModelAndView modelAndView = new ModelAndView();
		if (convenio.getNomeConvenio() == null || convenio.getNomeConvenio().isEmpty()) {
			result.rejectValue("tipoProcedimento", "Tipo de Procedimento é obrigatório.");
		}
		if (result.hasErrors()) {
			modelAndView.addObject("convenio", convenio);
		} else {
			repository.save(convenio);
		}
		modelAndView.setViewName("redirect:/convenios");
		return modelAndView;
	}

	@GetMapping("/convenios/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		repository.delete(id);
		modelAndView.setViewName("redirect:/convenios");
		return modelAndView;
	}
}
