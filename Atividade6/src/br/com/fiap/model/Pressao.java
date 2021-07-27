package br.com.fiap.model;

import java.util.Date;

public class Pressao {
	private Integer cdpressao;
	private Integer pressaod;
	private Integer pressaos;
	private Date pressaodata;

	public Pressao() {
		
	}

	public Pressao(Integer cdpressao, Integer pressaod, Integer pressaos, Date pressaodata) {
		super();
		this.cdpressao = cdpressao;
		this.pressaod = pressaod;
		this.pressaos = pressaos;
		this.pressaodata = pressaodata;
	}
	public Pressao(Integer cdpressao, Integer pressaod, Integer pressaos) {
		this.cdpressao = cdpressao;
		this.pressaod = pressaod;
		this.pressaos = pressaos;
	}
	public Pressao(Integer pressaod, Integer pressaos) {
		this.pressaod = pressaod;
		this.pressaos = pressaos;
	}

	public Integer getCdpressao() {
		return cdpressao;
	}

	public void setCdpressao(Integer cdpressao) {
		this.cdpressao = cdpressao;
	}

	public Integer getPressaod() {
		return pressaod;
	}

	public void setPressaod(Integer pressaod) {
		this.pressaod = pressaod;
	}

	public Integer getPressaos() {
		return pressaos;
	}

	public void setPressaos(Integer pressaos) {
		this.pressaos = pressaos;
	}

	public Date getPressaodata() {
		return pressaodata;
	}

	public void setPressaodata(Date pressaodata) {
		this.pressaodata = pressaodata;
	}

	@Override
	public String toString() {
		return "Pressao [cdpressao=" + cdpressao + ", pressaod=" + pressaod + ", pressaos=" + pressaos
				+ ", pressaodata=" + pressaodata + "]";
	}
	

}