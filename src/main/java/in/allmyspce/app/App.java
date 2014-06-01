package in.allmyspce.app;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.beans.PropertyVetoException;

@Configuration
//@ComponentScan(basePackages = "in.allmyspace.app")
@PropertySource(value = "classpath:/dev/application.properties")
@EnableWebMvc
@EnableTransactionManagement
public class App extends WebMvcConfigurerAdapter {


    @Bean
    public  JdbcTemplate jdbcTemplate(@Value("${db.url}") String url,
                                            @Value("${db.driver}") String driver
                                           ) throws PropertyVetoException {

        BasicDataSource dataSource=createDataSource(url,driver,1,1);
        return new JdbcTemplate(dataSource);

    }

    private BasicDataSource createDataSource(String url, String driver, int timeout, int poolSize) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setMaxActive(poolSize);
        dataSource.setMaxWait(timeout);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setValidationQuery("select 1");

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        return dataSource;

    }
    @Bean
    public DbxAppInfo createDBAppinfo(){

        return new DbxAppInfo("0iy80szhisdycxk","m3ulpaqq3rnrydp");
    }
    @Bean
    public DbxRequestConfig createDbxRequestConfig(){
        return new DbxRequestConfig("AllMySpaceDev/0.1", null);
    }



    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}