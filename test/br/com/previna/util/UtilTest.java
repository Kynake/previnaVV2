package br.com.previna.util;

import br.com.previna.model.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UtilTest {
    public static User createUser() {
        User user = new User();
        user.setCpf("12345678909");
        user.setName("Fulano de Tal");
        user.setEmail("fulano@gmail.com");
        try {
            user.setPassword(EncryptUtil.encrypt2("ages2017"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setActive(true);
        return user;
    }

    public static Pergunta createPergunta() {

        Pergunta pergunta = new Pergunta();
        pergunta.setDescricao("Qual melhor liquido a ser bebido?");
        pergunta.setNivel(1);
        pergunta.setPremio(1000);
        Set<Resposta> listRP = creaResposta(pergunta);

        Eixo eixo=new Eixo();
        eixo.setId(1L);
        AgeGroup ageGroup  = new AgeGroup();
        ageGroup.setId(1L);
        pergunta.setAgeGroup(ageGroup);
        pergunta.setEixo(eixo);
        pergunta.setRespostas(listRP);

        return pergunta;

    }

    public static Set<Resposta> creaResposta(Pergunta pergunta) {
        Set<Resposta> listRP = new HashSet<>();
        Resposta resposta = new Resposta();
        resposta.setCerto(true);
        resposta.setDescricao("É agua irmão");
        resposta.setPergunta(pergunta);
        Resposta rpErrada = new Resposta();
        rpErrada.setDescricao("Refrigerante de cola");
        rpErrada.setCerto(false);
        rpErrada.setPergunta(pergunta);
        listRP.add(rpErrada);
        listRP.add(resposta);
        return listRP;
    }
}
