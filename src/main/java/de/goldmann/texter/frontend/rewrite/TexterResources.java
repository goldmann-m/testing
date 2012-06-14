package de.goldmann.texter.frontend.rewrite;

import org.ocpsoft.rewrite.config.Condition;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.event.Rewrite;
import org.ocpsoft.rewrite.servlet.config.Path;

public abstract class TexterResources {
	public static Condition excluded()
	   {
	      return new Condition() {
	         @Override
	         public boolean evaluate(final Rewrite event, final EvaluationContext context)
	         {
	            return Path.matches(".*")
	                     .andNot(Path.matches(".*javax\\.faces\\.resource.*"))
	                     .andNot(Path.matches("/logout"))
	                     .andNot(Path.matches("/rfRes/.*")).evaluate(event, context);
	         }
	      };
	   }

}
