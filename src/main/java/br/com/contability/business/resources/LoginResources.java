package br.com.contability.business.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.contability.business.Usuario;
import br.com.contability.business.services.CodigoUsuarioServices;
import br.com.contability.comum.AuthenticationAbstract;
import br.com.contability.comum.RedirectAttributesAbstract;
import br.com.contability.comum.StringPaginasAndRedirect;
import net.sf.jasperreports.engine.JREmptyDataSource;

@Controller
@RequestMapping(value = "/login")
public class LoginResources {
	
	@Autowired
	private AuthenticationAbstract auth;
	
	@Autowired
	private CodigoUsuarioServices services;
	
	@Autowired
	private RedirectAttributesAbstract redirect;
	
	@GetMapping
	public String login(){ // pode ser também. @AuthenticationPrincipal User user

		Usuario usuario = auth.getAutenticacao();
		
		if (usuario != null)
			return "redirect:index";
		
		return "Login";
		
	}
	
	/*@Autowired private ApplicationContext appContext;
	@Autowired private DataSource dataSource;*/
	
	@GetMapping("/testando")
	public ModelAndView getRpt4(ModelMap modelMap, ModelAndView modelAndView) {
		
		modelMap.put("datasource", new JREmptyDataSource()); // AQUI COM CERTEZA VEM MINHA CONEXÃO
		modelMap.put("format", "pdf");
		modelAndView = new ModelAndView("rpt_HelloWorld", modelMap);
		return modelAndView;
		/*
		JasperReportsPdfView view = new JasperReportsPdfView();
	    view.setJdbcDataSource(dataSource);
	    view.setUrl("classpath:/jasperreports/rpt_HelloWorld.jrxml");
	    Map<String, Object> params = new HashMap<>();
	    params.put("param1", "param1 value");
	    params.put("datasource", new JREmptyDataSource());
	    params.put("datasource", "pdf");
	    view.setApplicationContext(appContext);
	    
	    return new ModelAndView(view, params);*/
	}
	
	@GetMapping("/requestcode")
	public String loginConfirmacao(@RequestParam("codigo") String codigo, RedirectAttributes attributes) {
		redirect.setRedirectAttributes(attributes);
		
		services.confirmaCodigoUsuario(codigo);
		attributes.addFlashAttribute("mensagem", "Usuario ativado, por favor digite o usuario e senha");
		
		return StringPaginasAndRedirect.LOGIN;
		
	}
	
	//	APRENDIZAGEM ABAIXO. NADA FUNCIONOU
	/*@RequestMapping(value = "/login")
	public ModelAndView paginLogin(){
		
		ModelAndView mv = new ModelAndView("Login");
		return mv;
		
	}*/
	/*@RequestMapping(value = "/autorizado")
	public String loginSucesso(){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		
		return "Login";
		
	}*/
	
	//RequestMapping(value = "/login", method = RequestMethod.POST)
	//public ModelAndView loginn(LoginUsuario loginUsuario){ // pode ser também. @AuthenticationPrincipal User user
		
		//System.out.println("passei");
		
		//if(result.hasErrors()){
			//return login(loginUsuario);
		//}
		
		//System.out.println(loginUsuario.getNome());
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//ModelAndView mv = new ModelAndView();
		
		//System.out.println(auth.getName());
		
//		if(auth == null){
			//mv = new ModelAndView("/Login"); //caminho
			//return mv.addObject("incorrect", true);
//		}	
//		return mv = new ModelAndView("/usuario");
		
	//}
	
	/*@RequestMapping("/usuario")
	public String login(){
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(" email " + auth.getName());
		
		System.out.println(user.getUsername());
		
		return "";
	}*/

}
