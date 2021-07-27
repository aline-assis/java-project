package br.com.fiap.servlets;

import java.io.IOException;


import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//import com.google.gson.Gson;

import br.com.fiap.model.Pressao;
import fiap.com.br.dao.PressaoDAO;
import br.com.fiap.exception.DBException;
import br.com.fiap.factory.DAOFactory;



/**
 * Servlet implementation class PressaoServletShow
 */
@WebServlet("/pressao")
public class PressaoServletShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PressaoDAO dao;
       
    public PressaoServletShow() {
        super();
        // TODO Auto-generated constructor stub
        dao = DAOFactory.getPressaoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map <String, Object> map = new HashMap<String, Object>();
		if(request.getParameterMap().containsKey("id")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			map.put("list", dao.buscar(id));
		} else {
			/* IRÁ RETORNAR A LISTA COM TODOS OS ITENS CADASTRADOS PARA O USUÁRIO */
			map.put("list", dao.listar());
		}
		write(response, map);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* CRIAR NOVO PRESSAO */
		Map <String, Object> map = new HashMap<String, Object>();

		try {
			Integer pressaoD = Integer.parseInt(request.getParameter("diastolic"));
			Integer pressaoS = Integer.parseInt(request.getParameter("sistolic"));
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			Pressao addPressao = new Pressao(pressaoD, pressaoS);
			dao.cadastrar(addPressao, userId);
			map.put("isValid", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("isValid", false);
		}
		write(response, map);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* EDITAR PRESSAO EXISTENTE */
		Map <String, Object> map = new HashMap<String, Object>();
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Integer pressaoD = Integer.parseInt(request.getParameter("diastolic"));
			Integer pressaoS = Integer.parseInt(request.getParameter("sistolic"));
			if(id != null && pressaoD != null && pressaoS != null) {
				Pressao updatePressao = new Pressao(id, pressaoD, pressaoS);
				dao.atualizar(updatePressao);
				map.put("isUpdated", id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("isUpdated", false);
		}
		write(response, map);
	}
	
	protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* EXCLUIR PRESSAO EXISTENTE */
		Map <String, Object> map = new HashMap<String, Object>();
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			dao.remover(id);
			map.put("isDeleted", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("isDeleted", false);
		}
		write(response, map);
	}
	
	private Date convertDate(String unparsedDate) throws IOException, ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(unparsedDate);
		return date;
	}
	
	private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
		response.setContentType("aplication/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}

}
