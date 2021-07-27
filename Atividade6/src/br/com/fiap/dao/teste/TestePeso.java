package br.com.fiap.dao.teste;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import br.com.fiap.model.Peso;
import fiap.com.br.dao.PesoDAO;
import br.com.fiap.dao.*;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;

public class TestePeso {

	public static void main(String[] args) {
		PesoDAO dao = DAOFactory.getPesoDAO();
		java.util.Date datapeso = new java.util.Date();
		//Cadastrar um produto
		Peso peso = new Peso(1,45,30,datapeso); 
		try {
			dao.cadastrar(peso, 1);
			System.out.println("Produto cadastrado.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Buscar um produto pelo código e atualizar
		
		//peso = dao.buscar(1);
		peso.setPesousuario(45);
		peso.setPesoideal(30);
		try {
			dao.atualizar(peso);
			System.out.println("Produto atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar os produtos
		List<Peso> lista = dao.listar();
		for (Peso item : lista) {
			System.out.println(item.getPesousuario() + " " + item.getPesoideal() + " " + item.getDatapeso());
		}
		
		//Remover um produto
		try {
			dao.remover(1);
			System.out.println("Produto removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}	
		/*try{
			UsuarioDAO dao = DAOFactory.getUsuarioDAO();
			java.util.Date datanascimento = new java.util.Date();
			Usuario usuario = new Usuario(1,"abc","aline",datanascimento, "a"); 
			System.out.println(usuario);
			dao.cadastrar(usuario);
		}
		catch(Exception e){
		System.out.println(e);
		} */
	}	
}