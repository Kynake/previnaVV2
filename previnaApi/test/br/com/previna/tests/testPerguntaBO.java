package br.com.previna.tests;

import br.com.previna.bo.PerguntaBO;
import br.com.previna.model.Pergunta;
import br.com.previna.model.Resposta;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class testPerguntaBO {
    private PerguntaBO perguntaBO = new PerguntaBO();
    private Pergunta pergunta;

    @Before
    public void setUp() {
        pergunta = new Pergunta();

    }


    @Test
    public void testaTamanhoDescricaoChao() {
        pergunta.setDescricao("Qual a melhor bebida para curar ressaca?");
        assertEquals(perguntaBO.validateSize(pergunta), true);

    }

    @Test
    public void testaTamanhoDescricaoChaoInferior() {
        pergunta.setDescricao("Oi,ta ok?");
        assertEquals(perguntaBO.validateSize(pergunta), false);

    }

    @Test
    public void testaTamanhoDescricaoTeto() {
        pergunta.setDescricao("Um importante princípio da biologia, foi quebrado com a descoberta do microquimerismo fetal. Microquimerismo é o nome dado ao fenômeno biológico referente a uma pequena população de células ou DNA presente em um indivíduo, mas derivada de um organismo geneticamente distinto. Investigando-se a presença do cromossomo Y foi revelado que diversos tecidos de mulheres continham células masculinas. A análise do histórico médico revelou uma correlação extremamente curiosa: apenas as mulheres que antes tiveram filhos homens apresentaram microquimerismo masculino.Preciso de mais 39 letras para testar ?");
        assertEquals(perguntaBO.validateSize(pergunta), true);

    }

    @Test
    public void testTamanhoDescricaoLimiteTetoSuperado() {
        pergunta.setDescricao("Um   importante princípio da biologia, foi quebrado com a descoberta do microquimerismo fetal. Microquimerismo é o nome dado ao fenômeno biológico referente a uma pequena população de células ou DNA presente em um indivíduo, mas derivada de um organismo geneticamente distinto. Investigando-se a presença do cromossomo Y foi revelado que diversos tecidos de mulheres continham células masculinas. A análise do histórico médico revelou uma correlação extremamente curiosa: apenas as mulheres que antes tiveram filhos homens apresentaram microquimerismo masculino.Preciso de mais 39 letras para testar ?");
        assertEquals(perguntaBO.validateSize(pergunta), false);
    }

    @Test
    public void testAlgumaCertaSemCerta() {
        Resposta r = new Resposta();
        r.setCerto(false);
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(r);
        pergunta.setRespostas(respostas);
        assertEquals(perguntaBO.hasRightAnswer(pergunta), false);
    }

    @Test
    public void testAlgumaCertaComCerta() {
        Resposta r = new Resposta();
        r.setCerto(true);
        Set<Resposta> respostas = new HashSet<>();
        respostas.add(r);
        pergunta.setRespostas(respostas);
        assertEquals(perguntaBO.hasRightAnswer(pergunta), true);
    }

   /* @Test
    public void quantPremioChao() {
        pergunta.setPremio(1);
        assertEquals(perguntaBO.validatePrize(pergunta.getPremio()), true);
    }

    @Test
    public void quantPremioTeto() {
        pergunta.setPremio(9999);
        assertEquals(perguntaBO.validatePrize(pergunta.getPremio()), true);
    }

    @Test
    public void quantPremioChaoInferior() {
        pergunta.setPremio(0);
        assertEquals(perguntaBO.validatePrize(pergunta.getPremio()), false);
    }

    @Test
    public void quantPremioTetoSuperior() {
        pergunta.setPremio(10001);
        assertEquals(perguntaBO.validatePrize(pergunta), false);
    }*/


}
