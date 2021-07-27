package fiap.com.br.dao;

import java.util.List;

import br.com.fiap.exception.DBException;
import br.com.fiap.model.Sports;

public interface SportsDAO {
	void cadastrar(Sports sports, int id) throws DBException;
	void atualizar(Sports sports) throws DBException;
	void remover (int cdatividade) throws DBException;
	Sports buscar (int id);
	List<Sports> listar();

}
