package uk.co.probablyfine.gia;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GiaProcessor implements MethodInterceptor  {

	public Object invoke(final MethodInvocation method) throws Throwable {
		
		final String function = method.getMethod().getAnnotation(GiaEval.class).value();
		
		final Binding binding = new Binding();
		
		for ( int i = 0 ; i < method.getMethod().getParameterTypes().length ; i++ ) {
			String varname = "a_".concat(Integer.toString(i+1));
			binding.setVariable(varname, method.getArguments()[i]);
		}
		
		final GroovyShell shell = new GroovyShell(binding);

		final boolean value = (Boolean) shell.evaluate(function);
		
		if (value) 
			return method.proceed();
		
		throw new GiaException(String.format("Gia eval returned false: %s", method.getMethod().getName()));
		
	}
	
}