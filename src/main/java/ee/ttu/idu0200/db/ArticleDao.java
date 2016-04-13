package ee.ttu.idu0200.db;

import ee.ttu.idu0200.model.Article;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class ArticleDao {

  private static Logger LOG = getLogger(ArticleDao.class);

  public static final String SELECT_ALL_ARTICLES = "SELECT id, title, published_date, content FROM article";
  public static final String SELECT_ARTICLE_BY_ID = "SELECT id, title, published_date, content FROM article WHERE id = ?";
  public static final String UPDATE_ARTICLE = "UPDATE article SET title=?, published_date=?, content=? WHERE id=?";

  @Resource(name = "jdbc/dataSource")
  private DataSource dataSource;


  public Article findById(long id) {
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(SELECT_ARTICLE_BY_ID);
        sql.setLong(1, id);

        LOG.info("findById: id = " + id);

        ResultSet set = sql.executeQuery();
        if (set.next()) {
          return toArticle(set);
        }
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Article> findAll() {
    ArrayList<Article> articles = new ArrayList<>();
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(SELECT_ALL_ARTICLES);
        ResultSet set = sql.executeQuery();
        while (set.next()) {
          articles.add(toArticle(set));
        }
        LOG.info("findAll: size = " + articles.size());
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return articles;
  }

  public void update(Article article) {
    try {
      try (Connection conn = dataSource.getConnection()) {
        PreparedStatement sql = conn.prepareStatement(UPDATE_ARTICLE);
        sql.setString(1, article.getTitle());
        sql.setDate(2, new Date(article.getPublishedDate().getTime()));
        sql.setString(3, article.getContent());
        sql.setLong(4, article.getId());

        sql.executeUpdate();
        LOG.info("update: id = " + article.getId());
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private Article toArticle(ResultSet set) throws SQLException {
    Article article = new Article();
    article.setId(set.getLong("id"));
    article.setTitle(set.getString("title"));
    article.setPublishedDate(set.getDate("published_date"));
    article.setContent(set.getString("content"));
    return article;
  }


}
