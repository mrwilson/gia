package uk.co.probablyfine.gia;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GiaProcessor implements MethodInterceptor  {

	public Object invoke(MethodInvocation arg0) throws Throwable {
		
		String function = arg0.getMethod().getAnnotation(GiaEval.class).function();
		
		Binding binding = new Binding();
		
		for ( int i = 0 ; i < arg0.getMethod().getParameterTypes().length ; i++ ) {
			String varname = "a".concat(Integer.toString(i+1));
			System.out.println(varname);
			binding.setVariable(varname, arg0.getArguments()[i]);
			
		}
		
		GroovyShell shell = new GroovyShell(binding);

		boolean value = (Boolean) shell.evaluate(function);
		
		System.out.println(value);
		
		if (value) {
			System.out.println("Value was true, returning original argument");
			return arg0.proceed();
		} else {
			System.out.println("Value was false, returning false");
			return null;
		}
		
	
	}

	
}
