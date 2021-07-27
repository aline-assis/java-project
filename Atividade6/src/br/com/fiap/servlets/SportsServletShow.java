package br.com.fiap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Sports;
import fiap.com.br.dao.SportsDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/sports")
public class SportsServletShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SportsDAO dao;

	public SportsServletShow() {
        super();
        dao = DAOFactory.getSportsDAO();
    }
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map <String, Object> map = new HashMap<String, Object>();
		if(request.getParameterMap().containsKey("id")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			map.put("list", dao.buscar(id));
		} else {
			/* IR� RETORNAR A LISTA COM TODOS OS ITENS CADASTRADOS PARA O USU�RIO */
			map.put("list", dao.listar());
		}
		write(response, map);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* CRIAR NOVO SPORTS */

		Map <String, Object> map = new HashMap<String, Object>();
		try {
			String desc = request.getParameter("name");
			Integer calorias = Integer.parseInt(request.getParameter("calories"));
			Integer userId = Integer.parseInt(request.getParameter("userId"));
			Sports addSports = new Sports(desc, calorias);
			dao.cadastrar(addSports, userId);
			map.put("isValid", true);
			write(response, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* EDITAR Sport EXISTENTE */
		Map <String, Object> map = new HashMap<String, Object>();

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			String desc = request.getParameter("name");
			Integer calorias = Integer.parseInt(request.getParameter("calories"));

			if(id != null && desc != null && calorias != null) {

				Sports updateSport = new Sports(id,desc, calorias);
				dao.atualizar(updateSport);
				map.put("isUpdated", id);
			}	else {
				map.put("isUpdated", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("isUpdated", false);
		}
		write(response, map);
	}


	protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* EXCLUIR Sport EXISTENTE */
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