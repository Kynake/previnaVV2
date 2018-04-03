package br.com.previna.tests;

import br.com.previna.bo.EixoBO;
import br.com.previna.model.Eixo;
import br.com.previna.util.Validator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEixoBO {

    private Eixo eixo = new Eixo();
    private EixoBO eixoBO = new EixoBO();

    @Test
    public void testTamanhoEixoValido() {
        eixo.setName("psicologia");
        assertEquals(true, eixoBO.validate(eixo));

    }

    @Test
    public void testTamanhoEixoInvalidoFloor() {
        eixo.setName("ps");
        assertEquals(false, eixoBO.validate(eixo));

    }

    @Test
    public void testTamanhoEixoInvalidoCeiling() {
        eixo.setName("psicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsicopsico");
        assertEquals(false, eixoBO.validate(eixo));

    }


    @Test
    public void testNameEixoValido() {
        eixo.setName("psicologia");
        assertEquals(true, eixoBO.validate(eixo));

    }
    @Test
    public void testNameEixoInvalidoComArroba() {
        eixo.setName("psicologi@");
        assertEquals(false, eixoBO.validate(eixo));

    }
    @Test
    public void testNameEixoInvalidoComParenteses() {
        eixo.setName("psicologi)");
        assertEquals(false, eixoBO.validate(eixo));

    }
    @Test
    public void testNameEixoValidoComAcento() {
        eixo.setName("nutrição");
        assertEquals(true, eixoBO.validate(eixo));

    }





}
