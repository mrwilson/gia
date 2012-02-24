package uk.co.probablyfine.gia;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class GiaModule extends AbstractModule {

	@Override
	protected void configure() {
		GiaProcessor proc = new GiaProcessor();
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(GiaEval.class), proc);
	}

}
