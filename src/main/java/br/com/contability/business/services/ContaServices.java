package br.com.contability.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.contability.business.Conta;
import br.com.contability.business.Usuario;
import br.com.contability.business.repository.ContaRepository;
import br.com.contability.comum.ServicesAbstract;
import br.com.contability.exceptions.ObjetoInexistenteException;
import br.com.contability.exceptions.ObjetoInexistenteExceptionMessage;

@Service
public class ContaServices extends ServicesAbstract<Conta, ContaRepository> {
	
	@Autowired
	private TrataParametrosServices parametroServices;

	/**
	 * @param usuario
	 * @param conta
	 */
	public void gravaConta(Usuario usuario, Conta conta) {

		conta.setUsuario(usuario);

		if (conta.getId() == null) {
			super.insere(conta, null);
		} else {
			super.atualiza(conta, null);
		}

	}

	/**
	 * @param usuario
	 * @return
	 */
	public List<Conta> seleciona(Usuario usuario) {

		List<Conta> contas = super.getJpa().selecionaPelo(usuario.getId());

		return contas;
	}

	/**
	 * @param usuario
	 * @param mv
	 * @param id
	 * @return
	 */
	public ModelAndView getConta(Usuario usuario, ModelAndView mv, Object id) {
		
		Long idConta = parametroServices.trataParametroLong(id, "/conta");
		
		Optional<Conta> conta = super.getJpa().getConta(usuario.getId(), idConta);

		conta.orElseThrow(() -> new ObjetoInexistenteExceptionMessage("/conta", "Conta não encontrada"));

		mv.addObject("conta", conta.get());

		return mv;

	}

	public void removeConta(Usuario usuario, Long id) {

		if (id == null || confirmaVinculo(usuario, id))
			throw new ObjetoInexistenteException();

		super.remove(id, null);

	}

	private boolean confirmaVinculo(Usuario usuario, Long id) {

		Optional<Conta> conta = super.getJpa().getConta(usuario.getId(), id);

		return !conta.isPresent();
		
	}

	public Conta getPeloLancamento(Long idLancamento) {
		return super.getJpa().getPeloLancamento(idLancamento);
	}

	public Integer selecionaNumeroContasDo(Usuario usuario) {
		
		return super.getJpa().selecionaNumeroContasDo(usuario.getId());
	}

}
