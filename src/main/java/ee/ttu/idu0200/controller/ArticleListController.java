package ee.ttu.idu0200.controller;

import ee.ttu.idu0200.db.ArticleDao;
import ee.ttu.idu0200.model.Article;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ArticleListController {

  @Inject
  private ArticleDao articleDao;

  public String findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    List<Article> articleList = articleDao.findAll();
    req.setAttribute("articleList", articleList);

    return "/WEB-INF/jsp/article/articleList.jsp";
  }
}
