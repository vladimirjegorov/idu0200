package ee.ttu.idu0200.service;

import ee.ttu.idu0200.db.ArticleDao;
import ee.ttu.idu0200.model.Article;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.status;

@ManagedBean // For CDI to work
@Path("/")
public class ArticleListRestService {

  @Inject
  private ArticleDao articleDao;

  @GET
  @Produces(APPLICATION_JSON)
  public Response findAllArticles(@QueryParam("id") long id) {
    Article article = articleDao.findById(id);

    // return HTTP response 200 in case of success
    return status(200).entity(article).build();
  }

}
