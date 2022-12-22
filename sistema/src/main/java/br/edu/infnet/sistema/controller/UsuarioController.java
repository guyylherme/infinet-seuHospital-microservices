package br.edu.infnet.sistema.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.edu.infnet.sistema.controller.dto.UsuarioDTO; 

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	 	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${usuario.endpoint.url}")
	private String usuarioApiUrl;
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable String id) {
		
		logger.info("API SISTEMA - OBTENDO USUÁRIO POR ID: {}", id);
		ResponseEntity<UsuarioDTO> usuarioDTO = restTemplate.exchange(usuarioApiUrl+id, HttpMethod.GET, null, UsuarioDTO.class);
		logger.info("REALIZANDO CONSULTA NA API DO USUÁRIO: {}", usuarioDTO);
		
		return usuarioDTO;
	}
	
	@PostMapping(value = "/registrar")
	public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDTO form) {
		
		logger.info("API SISTEMA - CRIANDO NOVO USUÁRIO FORM: {}", form);			
		
		try {
			logger.info("API SISTEMA - ACESSANDO API DO USUÁRIO");	
			UsuarioDTO usuario = restTemplate.postForObject(usuarioApiUrl, form, UsuarioDTO.class);	
			logger.info("API SISTEMA - RETORNANDO DA API DO USUÁRIO DTO: {}", usuario);
			
			return ResponseEntity.ok(usuario);
			
		} catch (Exception e) {
			logger.error("API SISTEMA - ERRO AO ACESSAR API DO USUÁRIO: {}", e);	
			return ResponseEntity.notFound().build();
		} 
	}
	
	@PutMapping(value = "/editar/{id}")
	public ResponseEntity<UsuarioDTO> editarUsuario(@RequestBody @Valid UsuarioDTO form, @PathVariable Integer id) {
		
		logger.info("API SISTEMA - EDITANDO USUÁRIO DO ID: {} - FORMULARIO: {}", id, form);			
		
		try {
			logger.info("API SISTEMA - ACESSANDO API DO USUÁRIO");	
			UsuarioDTO usuarioDTO = restTemplate.getForObject(usuarioApiUrl+new UsuarioDTO(), UsuarioDTO.class);		
			logger.info("API SISTEMA - RETORNANDO DA API DO USUÁRIO DTO: {}", usuarioDTO);
			return ResponseEntity.ok(usuarioDTO);
			
		} catch (Exception e) {
			logger.error("API SISTEMA - ERRO AO ACESSAR API DO USUÁRIO: {}", e);	
			return ResponseEntity.notFound().build();
		} 
	}
	

}
