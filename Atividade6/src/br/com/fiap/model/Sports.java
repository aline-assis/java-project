package br.com.fiap.model;

import java.util.Date;

public class Sports {

	private Integer cdatividade;
	private String dsatividade;
	private Date dtatividade;
	private Integer caloriagasta;

	public Sports() {
		
	}

	public Integer getCdatividade() {
		return cdatividade;
	}

	public void setCdatividade(Integer cdatividade) {
		this.cdatividade = cdatividade;
	}

	public String getDsatividade() {
		return dsatividade;
	}

	public void setDsatividade(String dsatividade) {
		this.dsatividade = dsatividade;
	}

	public Date getDtatividade() {
		return dtatividade;
	}

	public void setDtatividade(Date dtatividade) {
		this.dtatividade = dtatividade;
	}

	public Integer getCaloriagasta() {
		return caloriagasta;
	}

	public void setCaloriagasta(Integer caloriagasta) {
		this.caloriagasta = caloriagasta;
	}

	public Sports(Integer cdatividade, String dsatividade, Date dtatividade, Integer caloriagasta) {
		super();
		this.cdatividade = cdatividade;
		this.dsatividade = dsatividade;
		this.dtatividade = dtatividade;
		this.caloriagasta = caloriagasta;
	}

	public Sports(Integer cdatividade, String dsatividade, Integer caloriagasta) {
		super();
		this.cdatividade = cdatividade;
		this.dsatividade = dsatividade;
		this.caloriagasta = caloriagasta;
	}

	public Sports( String dsatividade, Integer caloriagasta) {
		super();
		this.dsatividade = dsatividade;
		this.caloriagasta = caloriagasta;
	}


	@Override
	public String toString() {
		return "Atividade [cdatividade=" + cdatividade + ", dsatividade=" + dsatividade + ", dtatividade=" + dtatividade
				+ ", caloriagasta=" + caloriagasta + "]";
	}
	

}