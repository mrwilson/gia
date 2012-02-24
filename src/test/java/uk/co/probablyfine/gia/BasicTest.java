package uk.co.probablyfine.gia;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.Guice;

public class BasicTest {

	@Test
	public void testOne() {
		
		BasicTest bt = Guice.createInjector(new GiaModule()).getInstance(BasicTest.class);
		Assert.assertEquals(bt.function(1), 1);
		Assert.assertEquals(bt.function(2), 0);
		
	}
	
	@GiaEval(function="println a1; return a1 == 1;")
	public int function(int foo) {
		System.out.println("Foo: "+foo);
		return foo;
	}
	
}
