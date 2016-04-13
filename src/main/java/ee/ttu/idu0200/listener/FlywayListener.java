package ee.ttu.idu0200.listener;

import org.flywaydb.core.Flyway;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class FlywayListener implements ServletContextListener {

  @Resource(name = "jdbc/dataSource")
  private DataSource dataSource;

  public void contextInitialized(ServletContextEvent sce) {
    Flyway flyway = new Flyway();
    flyway.setDataSource(dataSource);
    flyway.migrate();
  }

  public void contextDestroyed(ServletContextEvent sce) {
  }
}
