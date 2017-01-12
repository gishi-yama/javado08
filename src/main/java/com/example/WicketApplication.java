package com.example;

import com.example.page.HomePage;
import com.google.inject.Module;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		initGuiice();

		// add your configuration here
	}

	/**
	 * Google Guiceを初期化する.
	 */
	private void initGuiice() {
		getComponentInstantiationListeners().add(new GuiceComponentInjector(this, getGuiceModule()));
	}

	/**
	 * @return injection module.
	 */
	private Module getGuiceModule() {
		return binder -> {

		};
	}
}
