package ee.ttu.idu0200;

import ee.ttu.idu0200.controller.ArticleController;
import ee.ttu.idu0200.controller.ArticleListController;
import ee.ttu.idu0200.validator.ArticleFormValidator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;

@WebServlet({"/article/s"})
public class ArticleServlet extends HttpServlet {

  @Inject
  private ArticleListController articleListController;

  @Inject
  private ArticleController articleController;


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String idParameter = req.getParameter("id");
    String viewName = "";

    if (isNotEmpty(idParameter) && isNumber(idParameter)) {
      viewName = articleController.findById(req, resp);
    } else {
      viewName = articleListController.findAll(req, resp);
    }
    getServletContext().getRequestDispatcher(viewName).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String viewName = "";

    String action = req.getParameter("action");
    if ("save".equals(action)) {
      viewName = articleController.save(req, resp);
    }

    getServletContext().getRequestDispatcher(viewName).forward(req, resp);
  }
}

// TODO: Tests for DAO
// TODO: Logs
// TODO: Ajax

// TODO: migrator ??

// TODO: Sequence diagrams
