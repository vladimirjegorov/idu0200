package ee.ttu.idu0200.controller;

import ee.ttu.idu0200.converter.ArticleConverter;
import ee.ttu.idu0200.db.ArticleDao;
import ee.ttu.idu0200.form.ArticleForm;
import ee.ttu.idu0200.model.Article;
import ee.ttu.idu0200.validator.ArticleFormValidator;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

public class ArticleController {

  public static final String ARTICLE_JSP = "/WEB-INF/jsp/article/article.jsp";
  public static final String ARTICLE_NOT_FOUND_JSP = "/WEB-INF/jsp/article/articleNotFound.jsp";

  @Inject
  private ArticleDao articleDao;

  @Inject
  private ArticleFormValidator articleFormValidator;

  @Inject
  private ArticleConverter articleConverter;

  public String findById(HttpServletRequest req, HttpServletResponse resp) {
    String idAttribute = req.getParameter("id");
    Long id = Long.valueOf(idAttribute);

    Article article = articleDao.findById(id);

    if (article == null) {
      return ARTICLE_NOT_FOUND_JSP;
    }
    ArticleForm articleForm = articleConverter.toArticleForm(article);

    req.setAttribute("article", articleForm);
    return ARTICLE_JSP;
  }

  public String save(HttpServletRequest req, HttpServletResponse resp) {
    ArticleForm articleForm = articleConverter.toArticleForm(req);
    Map<String, String> bindingResult = articleFormValidator.validate(articleForm);
    req.setAttribute("bindingResult", bindingResult);
    if (bindingResult.isEmpty()) {
      Article article = articleConverter.toArticle(articleForm);
      articleDao.update(article);
      Article articleReloaded = articleDao.findById(article.getId());
      ArticleForm articleFormReloaded = articleConverter.toArticleForm(articleReloaded);
      req.setAttribute("article", articleFormReloaded);
      req.setAttribute("successMessage", "saveSuccess");
    } else {
      req.setAttribute("article", articleForm);
    }

    return ARTICLE_JSP;
  }
}
