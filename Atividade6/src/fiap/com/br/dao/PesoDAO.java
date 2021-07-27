package fiap.com.br.dao;

import java.util.List;
import br.com.fiap.model.*;
import br.com.fiap.exception.*;
public interface PesoDAO {
	void cadastrar(Peso peso, int id) throws DBException;
	void atualizar(Peso peso) throws DBException;
	void remover (int cdpeso) throws DBException;
	Peso buscar (int id);
	List<Peso> listar();
}
