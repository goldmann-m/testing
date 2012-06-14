package de.goldmann.texter.frontend.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.goldmann.texter.frontend.user.UserBean;
import de.goldmann.texter.model.Article;
import de.goldmann.texter.services.ArticleService;

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
	
	private List<Article> allArticles = new ArrayList<>();

	private String title;
	private String content;
	
	@Inject
	private UserBean userBean;

	@Inject
	private ArticleService articleService;
	
	public void init(){
		this.allArticles = articleService.getAllArticlesFromUser(userBean.getUser().getId());
	}

	public void saveArticle(){
		Article article = new Article();
		article.setAuthor(userBean.getUser());
		article.setCreation(new Date());
		article.setContent(content);
		article.setTitle(title);
		
		allArticles.add(article);
		articleService.createArticle(article);
		
		title = "";
		content = "";
	}
	
	public List<Article> getAllArticles() {
		return allArticles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
