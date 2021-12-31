package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

@SpringBootApplication
@RestController
public class BackendApplication extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().authorizeRequests().anyRequest().authenticated();
  }

  @Bean
  HeaderHttpSessionStrategy sessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }

  @RequestMapping("/")
  @CrossOrigin(origins = "*", maxAge = 3600,
          allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
  public Message home() {
    return new Message("Hello World");
  }

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

}

