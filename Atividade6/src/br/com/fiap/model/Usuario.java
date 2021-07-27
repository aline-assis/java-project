package br.com.fiap.model;

import java.util.Date;

public class Usuario {
	private Integer cdusuario;
	private String password;
	private String nome;
	private Date datanascimento;
	private String dsemail;
	/*public Boolean deletar() { 
		System.out.println("Usuario deletado.");
     	return true;}
	
       Construtor de classe da classe conta
	   Encapsulamento para não ter dois usuários com mesmo ID após implementação de banco de dados
	 */
	
	public Usuario () {}
	

	public Usuario(Integer cdusuario, String password, String nome, Date datanascimento, String dsemail) {
		super();
		this.cdusuario = cdusuario;
		this.password = password;
		this.nome = nome;
		this.datanascimento = datanascimento;
		this.dsemail = dsemail;
	}
	
	public Usuario(String nome, Date datanascimento, String dsemail, String password) {
		this.password = password;
		this.nome = nome;
		this.datanascimento = datanascimento;
		this.dsemail = dsemail;
	}

	public Integer getCdusuario() {
		return cdusuario;
	}

	public void setCdusuario(Integer cdusuario) {
		this.cdusuario = cdusuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}
	

	public String getDsemail() {
		return dsemail;
	}

	public void setDsemail(String dsemail) {
		this.dsemail = dsemail;
	}

	@Override
	public String toString() {
		return "Usuario [password=" + password + ", nome=" + nome + ", datanascimento="
				+ datanascimento + ", dsemail=" + dsemail + "]";
	}

		

}
