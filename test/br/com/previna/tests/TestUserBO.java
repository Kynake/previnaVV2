package br.com.previna.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.previna.util.Validator;
import br.com.previna.util.Validator.validaCpf;

public class TestUserBO {
	@Test
	public void testCPFTrue() {
		assertEquals(true, Validator.validaCpf.fazConta(("61642096091")));
	}
	@Test
	public void testCPFFalse() {
		assertEquals(false, Validator.validaCpf.fazConta(("85172596021")));
	}

}
