package de.goldmann.texter.frontend.article;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.goldmann.texter.model.Article;

/**
 * Bean for article handling.
 * 
 * @author goldmannm
 * 
 */
@SessionScoped
@Named
public class ArticleBean implements Serializable {

	private static final long serialVersionUID = -4579660755493639097L;
	
	private List<Article> allArticles;

}
