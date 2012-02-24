package uk.co.probablyfine.gia;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GiaProcessor implements MethodInterceptor  {

	public Object invoke(MethodInvocation arg0) throws Throwable {
		
		String function = arg0.getMethod().getAnnotation(GiaEval.class).function();
		
		Binding binding = new Binding();
		binding.setVariable("foo", new Integer(2));
		GroovyShell shell = new GroovyShell(binding);

		Object value = shell.evaluate("println 'Hello World!'; x = 123; return foo * 10");

		
		
		return null;
	
	}

	
}
