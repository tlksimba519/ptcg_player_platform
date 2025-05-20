package tw.com.panmed.ptcg_player_platform;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import com.github.wnameless.spring.boot.up.EnableSpringBootUp;
import com.github.wnameless.spring.boot.up.data.mongodb.config.EnableSpringBootUpMongo;
import com.github.wnameless.spring.boot.up.data.mongodb.converter.MongoConverters;
import com.github.wnameless.spring.boot.up.jsf.config.EnableSpringBootUpJSF;
import com.github.wnameless.spring.boot.up.web.config.EnableSpringBootUpWeb;
import com.github.wnameless.spring.boot.up.web.utils.MessageSourceUtils;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import jakarta.annotation.PostConstruct;

@EnableSpringBootUp
@EnableSpringBootUpWeb
@EnableSpringBootUpJSF
@EnableSpringBootUpMongo(allowAnnotationDrivenEvent = true)
@EnableMongoAuditing
@EnableAsync
@Configuration
public class AppConfig implements WebMvcConfigurer {

  public static final Set<String> SYS_ADMIN_USERNAMES = Set.of("PTCG@gmail.com", "ptcg");

  @Bean
  public MongoCustomConversions mongoCustomConversions() {
    return MongoConverters.javaTimeConversions();
  }

  @PostConstruct
  void configJsonPath() {
    com.jayway.jsonpath.Configuration.setDefaults(new com.jayway.jsonpath.Configuration.Defaults() {

      private final JsonProvider jsonProvider = new JacksonJsonProvider();
      private final MappingProvider mappingProvider = new JacksonMappingProvider();

      @Override
      public JsonProvider jsonProvider() {
        return jsonProvider;
      }

      @Override
      public MappingProvider mappingProvider() {
        return mappingProvider;
      }

      @Override
      public Set<Option> options() {
        return EnumSet.noneOf(Option.class);
      }

    });
  }

  @Bean
  MessageSource messageSource() throws IOException {
    return MessageSourceUtils.createByClasspathFolder("messages/", "messages");
  }

  @Bean
  LocalValidatorFactoryBean validatorFactoryBean() throws IOException {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource());
    return bean;
  }

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setDefaultLocale(Locale.US);
    return localeResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    return localeChangeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

}
