package br.com.previna.testsDAO;

import br.com.previna.dao.PerguntaDAO;
import br.com.previna.dao.RespostaDAO;
import br.com.previna.model.Pergunta;
import br.com.previna.model.Resposta;
import br.com.previna.util.SessionFactoryRule;
import br.com.previna.util.UtilTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author bruno.mazzardo
 * Classe para teste do DAO Hibernate
 */

/**
public class TestPerguntaDAO {

    Pergunta pergunta;
    @Rule
    public final SessionFactoryRule sf = new SessionFactoryRule();
    PerguntaDAO perguntaDAO;
// Teste de integraçao, para criação de tabelas

    @Test
    public void saveResposta() {
        List<Pergunta> perguntaList = perguntaDAO.listAll(Pergunta.class);
        assertTrue(perguntaList.size() > 0);

    }

    @Before
    public void testCreationTablePergunta() {
        pergunta = UtilTest.createPergunta();
        perguntaDAO = new PerguntaDAO();
        perguntaDAO.save(pergunta);
    }
}
*/
