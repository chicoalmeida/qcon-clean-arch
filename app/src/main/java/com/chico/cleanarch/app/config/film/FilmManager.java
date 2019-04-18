package com.chico.cleanarch.app.config.film;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilmManager {

  @Bean
  public Map<String, Integer> filmTitleManager() {
    return new HashMap<>() {{
      put("the force awakens", 7);
      put("revenge of the sith", 6);
      put("attack of the clones", 5);
      put("the phantom menace", 3);
      put("return of the jedi", 3);
      put("the empire strikes back", 2);
      put("a new hope", 1);
    }};
  }
}
