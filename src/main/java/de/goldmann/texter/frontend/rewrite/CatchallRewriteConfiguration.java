package de.goldmann.texter.frontend.rewrite;

import javax.servlet.ServletContext;

import org.ocpsoft.common.services.NonEnriching;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.DispatchType;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;

public class CatchallRewriteConfiguration extends HttpConfigurationProvider
		implements NonEnriching {
	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder
				.begin()

				// Block direct access to XHTML files
				.defineRule()
				.when(DispatchType.isRequest().and(Direction.isInbound())
						.and(TexterResources.excluded()))
				.perform(Forward.to("/404"));
	}

	@Override
	public int priority() {
		return 20;
	}

}
