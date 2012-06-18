package de.goldmann.texter.frontend.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
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
	
	private Article article = new Article();
	
	private boolean editMode = false;
	
	@Inject
	private UserBean userBean;

	@Inject
	private ArticleService articleService;
	
	@PostConstruct
	public void init(){
		this.allArticles = articleService.getAllArticles();
	}

	public void saveArticle(){
		article.setAuthor(userBean.getUser());
		article.setCreation(new Date());
		
		allArticles.add(article);
		articleService.createArticle(article);
		
		article = new Article();
	}
	
	public void prepareEditArticle(Article article){
		this.editMode = true;
		this.article = article;
	}
	
	public void removeArticle(Article article){
		articleService.removeArticle(article);
		allArticles.remove(article);
		this.article = new Article();
	}
	
	public void editArticle(){
		articleService.editArticle(article);
		this.article = new Article();
		this.editMode = false;
	}
	
	public List<Article> getUserArticles() {
		return requestUserArticles();
	}

	public List<Article> requestUserArticles(){
		if(userBean.isLoggedIn()){
			return articleService.getAllArticlesFromUser(userBean.getUser().getId());
		}
		return null;
	}
	
	public List<Article> getAllArticles() {
		return allArticles;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public boolean isEditMode() {
		return editMode;
	}
	
}
