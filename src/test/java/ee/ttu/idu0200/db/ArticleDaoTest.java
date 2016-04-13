package ee.ttu.idu0200.db;

import ee.ttu.idu0200.model.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleDaoTest {

  @InjectMocks
  private ArticleDao articleDao;
  @Mock
  private DataSource dataSourceMock;

  private Connection connectionMock;
  private PreparedStatement preparedStatementMock;

  @Before
  public void setUp() throws SQLException {
    connectionMock = mock(Connection.class);
    when(dataSourceMock.getConnection()).thenReturn(connectionMock);
    preparedStatementMock = mock(PreparedStatement.class);
    when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
  }

  @Test
  public void findByIdReturnsArticleWhenArticleFound() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(true);
    when(resultSetMock.getLong("id")).thenReturn(123L);
    when(resultSetMock.getString("title")).thenReturn("Title");
    Date publishedDate = new Date();
    when(resultSetMock.getDate("published_date")).thenReturn(new java.sql.Date(publishedDate.getTime()));
    when(resultSetMock.getString("content")).thenReturn("Content");

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    Article article = articleDao.findById(123L);

    verify(preparedStatementMock).setLong(1, 123L);
    assertEquals(123L, article.getId());
    assertEquals("Title", article.getTitle());
    assertEquals(publishedDate.getTime(), article.getPublishedDate().getTime());
    assertEquals("Content", article.getContent());
  }

  @Test
  public void findByIdReturnsNullWhenArticleNotFound() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(false);

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    Article article = articleDao.findById(123L);

    verify(preparedStatementMock).setLong(1, 123L);
    assertNull(article);
  }

  @Test
  public void findAllReturnsAListOfArticles() throws Exception {
    ResultSet resultSetMock = mock(ResultSet.class);
    when(resultSetMock.next()).thenReturn(true, true, false);
    when(resultSetMock.getLong("id")).thenReturn(123L, 124L);
    when(resultSetMock.getString("title")).thenReturn("Title1", "Title2");
    Date publishedDate1 = new Date();
    Date publishedDate2 = new Date();
    when(resultSetMock.getDate("published_date")).thenReturn(new java.sql.Date(publishedDate1.getTime()), new java.sql.Date(publishedDate2.getTime()));
    when(resultSetMock.getString("content")).thenReturn("Content1", "Content2");

    when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);

    List<Article> articleList = articleDao.findAll();

    Article article0 = articleList.get(0);
    assertEquals(123L, article0.getId());
    assertEquals("Title1", article0.getTitle());
    assertEquals(publishedDate1.getTime(), article0.getPublishedDate().getTime());
    assertEquals("Content1", article0.getContent());

    Article article1 = articleList.get(1);
    assertEquals(124L, article1.getId());
    assertEquals("Title2", article1.getTitle());
    assertEquals(publishedDate2.getTime(), article1.getPublishedDate().getTime());
    assertEquals("Content2", article1.getContent());
  }

  @Test
  public void updatesArticle() throws Exception {
    Article article = new Article();
    article.setId(123L);
    article.setTitle("Title");
    Date date = new Date();
    article.setPublishedDate(date);
    article.setContent("Content");

    articleDao.update(article);
    verify(preparedStatementMock).setString(1, "Title");
    verify(preparedStatementMock).setDate(2, new java.sql.Date(date.getTime()));
    verify(preparedStatementMock).setString(3, "Content");
    verify(preparedStatementMock).setLong(4, 123L);
    verify(preparedStatementMock).executeUpdate();
    verifyNoMoreInteractions(preparedStatementMock);
  }
}