package br.edu.infnet.usuario.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.edu.infnet.usuario.model.domain.Usuario;

public class AtualizarUsuarioForm {
		
	@NotNull(message = "Nome é obrigatório")
	@NotEmpty(message = "Nome é obrigatório")
	@Length(min = 2, message = "Nome inválido")
	private String nome;

	@NotNull(message = "E-mail é obrigatório")
	@NotEmpty(message = "E-mail é obrigatório")
	@Email(message = "Formato de e-mail inválido")
	private String email;	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AtualizarUsuarioForm [nome=" + nome + ", email=" + email + "]";
	}
	
	public Usuario acao(Usuario usuario) {

		usuario.setNome(nome);
		usuario.setEmail(email);
		
		return usuario;
	}

}
