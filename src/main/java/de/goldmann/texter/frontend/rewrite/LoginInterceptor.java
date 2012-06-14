package de.goldmann.texter.frontend.rewrite;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import de.goldmann.texter.frontend.user.UserBean;

public class LoginInterceptor extends HttpConfigurationProvider {

	@Inject
	private UserBean userBean;
	
	@Override
	public Configuration getConfiguration(ServletContext arg0) {
		ConfigurationBuilder config = ConfigurationBuilder.begin();

		if (userBean.isLoggedIn()) {
			return config
					.defineRule()
					.addRule(Join.path("/control/user/").to("/faces/control/user.xhtml"))
					.addRule(Join.path("/control/articles/").to("/faces/control/articles.xhtml"))
					.addRule(Join.path("/control/features/").to("/faces/control/features.xhtml"));
		}

		return config;
	}

	@Override
	public int priority() {
		return 10;
	}

}
