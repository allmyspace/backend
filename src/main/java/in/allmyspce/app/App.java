package in.allmyspce.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
    public static JdbcTemplate jdbcTemplate(@Value("${db.server}") String serverName,
                                            @Value("${db.port}") String portNumber,
                                            @Value("${db.user}") String username,
                                            @Value("${db.database}") String databaseName,
                                            @Value("${db.password}") String password) throws PropertyVetoException {

                         return null;
    }





    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}