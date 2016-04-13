package ee.ttu.idu0200;

import ee.ttu.idu0200.controller.ArticleController;
import ee.ttu.idu0200.controller.ArticleListController;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;
import static org.apache.logging.log4j.LogManager.getLogger;

@WebServlet({"/article/s"})
public class ArticleServlet extends HttpServlet {

  private static Logger LOG = getLogger(ArticleServlet.class);

  @Inject
  private ArticleListController articleListController;

  @Inject
  private ArticleController articleController;

  @Override
  public void init() throws ServletException {
    LOG.info("ArticleServlet.init() : I was created");
  }

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

// TODO: Ajax

// TODO: Sequence diagrams
