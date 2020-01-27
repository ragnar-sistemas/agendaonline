
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

import agendaonline.com.models.Paciente;
import agendaonline.com.repositories.ConvenioRepository;
import agendaonline.com.repositories.PacienteRepository;
import agendaonline.com.repositories.ProntuariosRepository;

@Controller
public class PacientesController {// terminar, colocar remove e edite

	@Autowired
	private PacienteRepository repository;

	@Autowired
	private ConvenioRepository convenioRepository;

	@Autowired
	private ProntuariosRepository prontuariosRepository;

	@RequestMapping(value = "/pacientes", method = RequestMethod.GET)
	public ModelAndView listaPacientes(Model model) {
		ModelAndView modelAndView = new ModelAndView("pacientes/listaPacientes");
		modelAndView.addObject("pacientes", repository.findAll());
		model.addAttribute("convenios", convenioRepository.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/pacientes", method = RequestMethod.POST)
	public ModelAndView cadastroPacientes(Paciente paciente, BindingResult result, RedirectAttributes attributes) {
		ModelAndView modelAndView = new ModelAndView();
		repository.save(paciente);
		modelAndView.setViewName("redirect:/pacientes");
		return modelAndView;
	}

	@RequestMapping(value = "/paciente/{nome}", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable("nome") Long id) {
		ModelAndView modelAndView = new ModelAndView("pacientes/pacienteDetalhes");
		Paciente paciente = repository.findOne(id);
		modelAndView.addObject("paciente", paciente);
		modelAndView.addObject("prontuarios", prontuariosRepository.findByPaciente(paciente));
		return modelAndView;
	}

	@GetMapping("/pacientes/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		repository.delete(id);
		modelAndView.setViewName("redirect:/pacientes");
		return modelAndView;
	}
}
