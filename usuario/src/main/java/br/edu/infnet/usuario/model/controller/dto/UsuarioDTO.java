package br.edu.infnet.usuario.model.controller.dto;

import br.edu.infnet.usuario.model.domain.Usuario;

public class UsuarioDTO {
	 
	private Integer id;
	private String nome; 
	private String email;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Integer id, String nome, String email) {  
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
	
	

}
