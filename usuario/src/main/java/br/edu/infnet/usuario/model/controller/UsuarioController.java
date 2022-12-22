package br.edu.infnet.usuario.model.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.infnet.usuario.model.controller.dto.UsuarioDTO;
import br.edu.infnet.usuario.model.domain.Usuario;
import br.edu.infnet.usuario.model.form.AtualizarUsuarioForm;
import br.edu.infnet.usuario.model.form.RegistrarUsuarioForm;
import br.edu.infnet.usuario.model.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@GetMapping("/{id}")
	public Usuario getUsuario(@PathVariable Integer id) {
		
		logger.info("API USUÁRIOS  - OBTENDO USUÁRIO POR ID: {}", id);
		return usuarioService.getById(id);
	}
	
	@PostMapping(value = "/registrar")
	public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody @Valid RegistrarUsuarioForm form, UriComponentsBuilder uriBuilder){
		logger.info("API USUÁRIOS  - REGISTRANDO NOVO USUÁRIO: {}", form);
		
		try {
			Usuario usuario = form.acao();
			usuarioService.registrar(usuario);
			
			URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();					
			return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));

		} catch (Exception e) {
			logger.error("API USUÁRIOS  - ERRO AO REGISTRAR USUÁRIO: {}", e);
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<UsuarioDTO> editarUsuario(@RequestBody @Valid AtualizarUsuarioForm form, @PathVariable Integer id, UriComponentsBuilder uriBuilder){
		
		logger.info("API USUÁRIOS  - ATUALIZANDO USUÁRIO DO ID: {} - FORMULARIO: {}", id, form);
				
		try {
			
			Usuario usuario = usuarioService.getById(id);			
			usuario = form.acao(usuario);			
			
			usuarioService.atualizar(usuario);
			
			URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();					
			return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
			
		} catch (Exception e) {
			logger.error("API USUÁRIOS  - ERRO AO ATUALIZAR USUÁRIO: {}", e);
			return ResponseEntity.notFound().build();
		}
		
	}
	
}
