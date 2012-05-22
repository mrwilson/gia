package uk.co.probablyfine.gia;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.inject.Guice;

public class BasicTest {
	
	@Test
	public void testSucceed() {
		final BasicTest bt = Guice.createInjector(new GiaModule()).getInstance(BasicTest.class);
		assertEquals(bt.function(1), 1);
	}
	
	@Test(expected=GiaException.class)
	public void testFailure() {
		final BasicTest bt = Guice.createInjector(new GiaModule()).getInstance(BasicTest.class);
		bt.function(2);
	}
	
	@GiaEval("a_1 == 1;")
	public int function(int foo) {
		return foo;
	}
	
}
