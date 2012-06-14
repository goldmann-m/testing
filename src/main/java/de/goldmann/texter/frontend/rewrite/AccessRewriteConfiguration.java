package de.goldmann.texter.frontend.rewrite;

import javax.servlet.ServletContext;

import org.ocpsoft.common.services.NonEnriching;
import org.ocpsoft.rewrite.bind.El;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Invoke;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.Response;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

public class AccessRewriteConfiguration extends HttpConfigurationProvider
		implements NonEnriching {

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder
				.begin()
				.addRule(Join.path("/").to("/index.xhtml"))
				.addRule(Join.path("/about/").to("/about.xhtml"))
				.addRule(Join.path("/contact/").to("/contact.xhtml"))
				.addRule(Join.path("/registry/").to("/registry/registry.xhtml"))
				.addRule(Join.path("/404").to("/HTTP404.xhtml").perform(Response.setCode(404)))

				// Authentication
				.defineRule()
				.when(Direction.isInbound().and(Path.matches("/logout")))
				.perform(
						Invoke.binding(El.retrievalMethod("userBean.logout"))
								.and(Redirect.temporary(context
										.getContextPath() + "/")));
	}

	@Override
	public int priority() {
		return 0;
	}

}
