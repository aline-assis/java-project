package fiap.com.br.dao;

import java.util.List;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.Usuario;

public interface UsuarioDAO {
	void cadastrar(Usuario usuario) throws DBException;
	void atualizar(Usuario usuario) throws DBException;
	void remover (int cdusuario) throws DBException;
	Usuario buscar (int id);
	List<Usuario> listar();
	Usuario logar(String email, String password);
}
