package agendaonline.com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import agendaonline.com.models.Procedimento;
import agendaonline.com.repositories.ProcedimentoRepository;

@Controller
public class ProcedimentosController {

	private static final String PROCEDIMENTOS = "procedimentos";
	private static final String TIPO_PROCEDIMENTO = "tipoProcedimento";
	private static final String PROCEDIMENTO = "procedimento";
	private static final String REDIRECT = "redirect:";
	private static final String LISTAR_PROCEDIMENTOS = "/listarProcedimentos";
	private static final String REDIRECT_LISTA_PROCEDIMENTOS = REDIRECT + LISTAR_PROCEDIMENTOS;

	@Autowired
	private ProcedimentoRepository repository;

	@RequestMapping(LISTAR_PROCEDIMENTOS)
	public ModelAndView listarProcedimentos() {
		ModelAndView mv = new ModelAndView(PROCEDIMENTOS + LISTAR_PROCEDIMENTOS);
		mv.addObject(PROCEDIMENTOS, repository.findAll());
		return mv;
	}

	@RequestMapping(value = "/cadastroProcedimento", method = RequestMethod.GET)
	public String cadastroProcedimento() {
		return "cadastroProcedimento";
	}

	@RequestMapping(value = "/salvaProcedimento", method = RequestMethod.POST)
	public ModelAndView salvaProcedimento(@Valid Procedimento procedimento, BindingResult result, RedirectAttributes attributes) {
		ModelAndView modelAndView = new ModelAndView();
		if (procedimento.getTipoProcedimento() == null || procedimento.getTipoProcedimento().isEmpty()) {
			result.rejectValue(TIPO_PROCEDIMENTO, "Tipo de Procedimento é obrigatório.");
		}
		if (result.hasErrors()) {
			modelAndView.addObject(PROCEDIMENTO, procedimento);
		} else {
			repository.save(procedimento);
		}
		modelAndView.setViewName(REDIRECT_LISTA_PROCEDIMENTOS);
		return modelAndView;
	}

	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("procedimentos/inserir");
		modelAndView.addObject(PROCEDIMENTO, new Procedimento());
		return modelAndView;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Procedimento procedimento, BindingResult result, HttpServletRequest request) {
		return getModelAndView(procedimento, result, "inserir", request);
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Procedimento procedimento = repository.findOne(id);
		modelAndView.addObject(PROCEDIMENTO, procedimento);
		modelAndView.setViewName(PROCEDIMENTO + "/alterar");
		return modelAndView;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Procedimento procedimento, BindingResult result, HttpServletRequest request) {
		return getModelAndView(procedimento, result, "alterar", request);
	}

	private ModelAndView getModelAndView(@Valid Procedimento tarefa, BindingResult result, String action, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
//		if (tarefa.getDataExpiracao() == null) {
//			result.rejectValue("dataExpiracao", "data.nao.pode.ser.nulo", "A data é obrigatória");
//		} else if (isMenor(tarefa.getDataExpiracao())) {
//			result.rejectValue("dataExpiracao", "data.nao.pode.ser.anterior.a.data.atual", "A data não pode ser anterior a data atual");
//		}
//		if (result.hasErrors()) {
//			modelAndView.setViewName("tarefas/" + action);
//			modelAndView.addObject("tarefa", tarefa);
//		} else {
//			tarefa.setUsuario(usuarioService.retornaUsuarioPeloEmail(request.getUserPrincipal().getName()));
//			repository.save(tarefa);
//			modelAndView.setViewName("redirect:/tarefas/listar");
//		}
		return modelAndView;
	}

	@GetMapping("/procedimentos/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		repository.delete(id);
		modelAndView.setViewName(REDIRECT_LISTA_PROCEDIMENTOS);
		return modelAndView;
	}
}
