package ee.ttu.idu0200.converter;

import ee.ttu.idu0200.form.ArticleForm;
import ee.ttu.idu0200.model.Article;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class ArticleConverter {

  public ArticleForm toArticleForm(HttpServletRequest request) {
    ArticleForm articleForm = new ArticleForm();
    articleForm.setId(request.getParameter("id"));
    articleForm.setTitle(request.getParameter("title"));
    articleForm.setPublishedDate(request.getParameter("publishedDate"));
    articleForm.setContent(request.getParameter("content"));

    return articleForm;
  }

  public ArticleForm toArticleForm(Article article) {
    ArticleForm articleForm = new ArticleForm();
    articleForm.setId(String.valueOf(article.getId()));
    articleForm.setTitle(article.getTitle());
    articleForm.setPublishedDate(String.valueOf(article.getPublishedDate()));
    articleForm.setContent(article.getContent());
    return articleForm;
  }

  public Article toArticle(ArticleForm articleForm) {
    Article article = new Article();
    article.setId(Long.valueOf(articleForm.getId()));
    article.setTitle(articleForm.getTitle());

    try {
      // Date from input='date' comes in format yyyy-MM-dd
      // http://stackoverflow.com/questions/7372038/is-there-any-way-to-change-input-type-date-format
      Date publishedDate = parseDate(articleForm.getPublishedDate(), "yyyy-MM-dd");
      article.setPublishedDate(publishedDate);
    } catch (ParseException e) {
      // Should not happen
    }
    article.setContent(articleForm.getContent());

    return article;
  }
}
