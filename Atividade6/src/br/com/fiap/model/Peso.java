package br.com.fiap.model;


import java.util.Date;

public class Peso {
	
		private Integer cdpeso;
		private Integer pesousuario;
		private Integer pesoideal;
		private Date    datapeso;
		

		public Peso(Integer cdpeso, Integer pesousuario, Integer pesoideal, Date datapeso) {
				super();
				this.cdpeso = cdpeso;
				this.pesousuario = pesousuario;
				this.pesoideal = pesoideal;
				this.datapeso = datapeso;
			
		}
		
		
		public Peso(Integer cdpeso, Integer pesousuario, Integer pesoideal) {
			super();
			this.cdpeso = cdpeso;
			this.pesousuario = pesousuario;
			this.pesoideal = pesoideal;	
		}
		
		
		public Peso(Integer pesousuario, Integer pesoideal) {
			super();
			this.pesousuario = pesousuario;
			this.pesoideal = pesoideal;
		}
		

		public Integer getCdpeso() {
			return cdpeso;
		}


		public void setCdpeso(Integer cdpeso) {
			this.cdpeso = cdpeso;
		}


		public Integer getPesousuario() {
			return pesousuario;
		}


		public void setPesousuario(Integer pesousuario) {
			this.pesousuario = pesousuario;
		}


		public Integer getPesoideal() {
			return pesoideal;
		}


		public void setPesoideal(Integer pesoideal) {
			this.pesoideal = pesoideal;
		}


		public Date getDatapeso() {
			return datapeso;
		}


		public void setDatapeso(Date datapeso) {
			this.datapeso = datapeso;
		}


	public Peso() {
		
	}


	@Override
	public String toString() {
		return "Peso [cdpeso=" + cdpeso + ", pesousuario=" + pesousuario + ", pesoideal=" + pesoideal + ", datapeso="
				+ datapeso + "]";
	}

}