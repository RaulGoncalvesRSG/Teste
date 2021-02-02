package com.curso.boot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.curso.boot.model.Departamento;
import com.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
    private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";		//Envia a instância do obj para o th:object da pag
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.buscarTodos());
		return "/departamento/lista";
	}
	
	/*@Valid - essa anotação q vai dizer ao spring q estamos realizando a validação e a beanValidation para o obj
	Departamento q vem do formulário
	BindingResult - esse obj é do spring e ele verifica se houve algum problema referente às validações*/
	@PostMapping("/salvar")					//Redireciona para a própria página
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		service.salvar(departamento);		//attr envia msg para a página
		attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")		//ModelMap model é utilizado para enviar o obj para o formulário da pag
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id));
		return "/departamento/cadastro";
	}
	
	/*RedirectAttributes pq está fazendo um redirecionamento
	 O addFlashAttribute() deve ser usado quando o retorno do seu método será um redirect. Já o addAttribute() é 
	 usado para retornos do tipo forward. No Spring MVC, quando vc N usar a palavra "redirect:" na string de retorno
	 o retorno será forward*/
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult result,  RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/departamento/cadastro";
		}
		
		service.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")		//@PathVariable recupera o ID da URL
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		}
		else {
			service.excluir(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}
		return listar(model);		//Pode fazer um redirect ou chamar o método listar (ModelMap)
	}
}
