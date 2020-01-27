package agendaonline.com.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendaonline.com.models.Procedimento;
import agendaonline.com.repositories.ProcedimentoRepository;

@Controller
public class ProcedimentosController {

	@Autowired
	private ProcedimentoRepository repository;

	@RequestMapping("/procedimentos")
	public ModelAndView listaProcedimentos() {
		ModelAndView mv = new ModelAndView("procedimentos/listaProcedimentos");
		mv.addObject("procedimentos", repository.findAll());
		return mv;
	}

	@RequestMapping(value = "/procedimentos", method = RequestMethod.POST)
	public ModelAndView cadastroProcedimento(@Valid Procedimento procedimento, BindingResult result, RedirectAttributes attributes) {
		ModelAndView modelAndView = new ModelAndView();
		if (procedimento.getTipoProcedimento() == null || procedimento.getTipoProcedimento().isEmpty()) {
			result.rejectValue("tipoProcedimento", "Tipo de Procedimento é obrigatório.");
		}
		if(result.hasErrors()){
			modelAndView.addObject("procedimento", procedimento);
		} else {
			repository.save(procedimento);
		}
		modelAndView.setViewName("redirect:/procedimentos");
		return modelAndView;
	}

	@GetMapping("/procedimentos/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		repository.delete(id);
		modelAndView.setViewName("redirect:/procedimentos");
		return modelAndView;
	}
}
