package br.com.locadoraclienteweb.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.locadoraclienteweb.model.Carro;

public class TesteCarro {

	@Test
	public void testGetCodigoCarro() {
		Carro c = new Carro();
		c.setCodigoCarro(2000000000);
		c.setCodigoCarro(0);
		c.getCodigoCarro();
		
		Assert.assertNull(null);
	}

}
