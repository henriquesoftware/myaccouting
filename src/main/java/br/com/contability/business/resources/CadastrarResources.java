package br.com.contability.business.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.contability.business.Cadastro;
import br.com.contability.business.services.CadastrarServices;
import br.com.contability.business.services.UsuarioServices;
import br.com.contability.comum.StringRedirecionamentoPaginas;

@Controller
@RequestMapping("/cadastrar")
public class CadastrarResources {
	
	@Autowired
	private CadastrarServices cadastrarServices;
	
	@Autowired
	private UsuarioServices usuarioServices;

	@GetMapping
	public ModelAndView novo(Cadastro cadastro){
		
		ModelAndView mv = new ModelAndView("Cadastrar");
		
		return mv;
	}
	
	@PostMapping()
	public ModelAndView cadastrar(@Valid Cadastro cadastro, BindingResult result, RedirectAttributes attributes, HttpServletRequest handler){
		if(result.hasErrors())
			novo(cadastro);
		
		usuarioServices.insere(cadastro);
		
//		System.out.println(handler.getRequestURL().toString());/*
		cadastrarServices.cadastraUsuario(cadastro);
		
		attributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso, por favor visualize o email enviado para confirmar o cadastro");
		
		return new ModelAndView(StringRedirecionamentoPaginas.LOGIN);
	}
	
}