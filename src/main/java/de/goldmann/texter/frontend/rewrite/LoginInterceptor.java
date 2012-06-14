package de.goldmann.texter.frontend.rewrite;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.servlet.config.HttpCondition;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.ocpsoft.rewrite.servlet.http.event.HttpServletRewrite;

import de.goldmann.texter.frontend.user.UserBean;

public class LoginInterceptor extends HttpConfigurationProvider {

	@Inject
	private UserBean userBean;
	
	@Override
	public Configuration getConfiguration(ServletContext arg0) {
		ConfigurationBuilder config = ConfigurationBuilder.begin();

		
			return config
					.defineRule().when(new HttpCondition() {
						
						@Override
						public boolean evaluateHttp(HttpServletRewrite event,
								EvaluationContext context) {
							return userBean.isLoggedIn(); 
						}
					})
					.addRule(Join.path("/control/user/").to("/faces/control/user.xhtml"))
					.addRule(Join.path("/control/articles/").to("/faces/control/articles.xhtml"))
					.addRule(Join.path("/control/features/").to("/faces/control/features.xhtml"));
	}

	@Override
	public int priority() {
		return 10;
	}

}
