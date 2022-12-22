package br.edu.infnet.usuario.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.usuario.model.controller.UsuarioController;
import br.edu.infnet.usuario.model.domain.Usuario;
import br.edu.infnet.usuario.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario getById(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	public void registrar(Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			logger.info("API CLIENTES - USUARIOSERVICE - ERRO AO SALVAR USUÁRIO NA BASE: {}", e);
		}
		
	}

	public void atualizar(Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			logger.info("API CLIENTES - USUARIOSERVICE - ERRO AO ATUALIZAR USUÁRIO NA BASE: {}", e);
		}		
	}
}
