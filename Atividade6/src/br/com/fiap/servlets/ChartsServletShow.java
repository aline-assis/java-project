package br.com.fiap.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fiap.factory.DAOFactory;
import fiap.com.br.dao.ChartsDAO;

/**
 * Servlet implementation class ChartsServletShow
 */
@WebServlet("/charts")
public class ChartsServletShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChartsDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartsServletShow() {
        super();
        dao = DAOFactory.getChartsDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map <String, Object> map = new HashMap<String, Object>();
		map.put("list", dao.listar());
		write(response, map);
	}

	private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
		response.setContentType("aplication/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(map));
	}
}
