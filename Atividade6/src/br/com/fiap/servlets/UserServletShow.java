package br.com.fiap.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Usuario;
import fiap.com.br.dao.UsuarioDAO;

/**
 * Servlet implementation class UserServletShow
 */
@WebServlet("/user")
public class UserServletShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServletShow() {
        super();
        // TODO Auto-generated constructor stub
        dao = DAOFactory.getUsuarioDAO();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map <String, Object> map = new HashMap<String, Object>();
		String actionType = request.getParameter("actionType");
		if(actionType.equals("register")) {
			try {
				String fullName = request.getParameter("fullName");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				Date birthdate = convertDate(request.getParameter("birthdate"));
				
				Usuario addUsuario = new Usuario(fullName, birthdate, email, password);
				dao.cadastrar(addUsuario);
				map.put("registered", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("registered", false);
			}

		}

		if(actionType.equals("login")){
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			map.put("userData", dao.logar(email, password));
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
