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
import org.ocpsoft.rewrite.servlet.config.rule.Join;

public class AccessRewriteConfiguration extends HttpConfigurationProvider implements NonEnriching {
	
	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin()
				.addRule(Join.path("/").to("/faces/index.xhtml"))
				.addRule(Join.path("/about/").to("/faces/about.xhtml"))
				.addRule(Join.path("/contact/").to("/faces/contact.xhtml"))
				.addRule(Join.path("/registry/").to("/faces/registry/registry.xhtml"))
				
				// Authentication
				.defineRule()
				.when(Direction.isInbound().and(Path.matches("/logout")))
				.perform(Invoke.binding(El.retrievalMethod("userBean.logout"))
						.and(Redirect.temporary(context
							.getContextPath() + "/")));
	}

	@Override
	public int priority() {
		return 0;
	}

}
