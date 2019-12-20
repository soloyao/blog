package com.zmy.pojo;

/**
 * @ClassName:Article
 * @Description:文章
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月19日 下午1:22:24
 */
public class Article {
	private int id;
	private long articleId;
	private String author;
	private String originalAuthor;
	private String articleTitle;
	private String publishDate;
	private String updateDate;
	private String articleContent;
	private String articleTags;
	private String articleType;
	private String articleCategories;
	private String articleUrl;
	private String articleTabloid;
	private long lastArticleId;
	private long nextArticleId;
	private int likes = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getOriginalAuthor() {
		return originalAuthor;
	}
	public void setOriginalAuthor(String originalAuthor) {
		this.originalAuthor = originalAuthor;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getArticleTags() {
		return articleTags;
	}
	public void setArticleTags(String articleTags) {
		this.articleTags = articleTags;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getArticleCategories() {
		return articleCategories;
	}
	public void setArticleCategories(String articleCategories) {
		this.articleCategories = articleCategories;
	}
	public String getArticleUrl() {
		return articleUrl;
	}
	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}
	public String getArticleTabloid() {
		return articleTabloid;
	}
	public void setArticleTabloid(String articleTabloid) {
		this.articleTabloid = articleTabloid;
	}
	public long getLastArticleId() {
		return lastArticleId;
	}
	public void setLastArticleId(long lastArticleId) {
		this.lastArticleId = lastArticleId;
	}
	public long getNextArticleId() {
		return nextArticleId;
	}
	public void setNextArticleId(long nextArticleId) {
		this.nextArticleId = nextArticleId;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public Article(int id, long articleId, String author, String originalAuthor, String articleTitle,
			String publishDate, String updateDate, String articleContent, String articleTags, String articleType,
			String articleCategories, String articleUrl, String articleTabloid, long lastArticleId, long nextArticleId,
			int likes) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.author = author;
		this.originalAuthor = originalAuthor;
		this.articleTitle = articleTitle;
		this.publishDate = publishDate;
		this.updateDate = updateDate;
		this.articleContent = articleContent;
		this.articleTags = articleTags;
		this.articleType = articleType;
		this.articleCategories = articleCategories;
		this.articleUrl = articleUrl;
		this.articleTabloid = articleTabloid;
		this.lastArticleId = lastArticleId;
		this.nextArticleId = nextArticleId;
		this.likes = likes;
	}
	public Article() {
		super();
	}
}
