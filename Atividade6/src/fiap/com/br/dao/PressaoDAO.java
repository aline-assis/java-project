package fiap.com.br.dao;
import java.util.List;
import br.com.fiap.model.*;
import br.com.fiap.exception.*;

public interface PressaoDAO {
		void cadastrar(Pressao pressao, int id) throws DBException;
		void atualizar(Pressao pressao) throws DBException;
		void remover (int cdpressao) throws DBException;
		Pressao buscar (int id);
		List<Pressao> listar();
		

}
