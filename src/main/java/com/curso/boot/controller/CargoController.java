package com.curso.boot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.curso.boot.model.Cargo;
import com.curso.boot.model.Departamento;
import com.curso.boot.service.CargoService;
import com.curso.boot.service.DepartamentoService;
import com.curso.boot.util.PaginacaoUtil;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page, 
										 @RequestParam("dir") Optional<String> dir) {
		/*Quando o user abrir a lista, ele n estará indicando a pág, então automaticamente irá para pag 1
		Isso ocorre pq a variável n terá um valor definido, se escolher, pega o valor q o usuário escolheu*/
		int paginaAtual = page.orElse(1);
		String ordem = dir.orElse("asc");		//Quando o user n clicar na coluna, a ordem será ascendente
		
		PaginacaoUtil<Cargo> pageCargo = cargoService.buscarPorPagina(paginaAtual, ordem);
		
		model.addAttribute("pageCargo", pageCargo);
	//	model.addAttribute("cargos", cargoService.buscarTodos());			//Sem paginação
		
		return "/cargo/lista"; 
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	/*O método editar não poderia ficar junto com o salvar, por exemplo: No salvar nós recebemos o departamento
	 *, então poderíamos apenas verificar se id do departamento é null então ele estaria salvando e se não 
	 *ele iria dar o update. Isso poderia ser feito no controller apenas? R = SIM*/
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/cargo/cadastro";
		}
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
	
	//Manda a lista de departamentos para a variável "departamentos" com a anotação 
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.buscarTodos();
	}	
}
