package io.lounge.configuration;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("LoungeY")
			.description("This page lists all the rest apis for LoungeY App.")
			.version("1.0")
			.build();
	}

	private Predicate<String> paths() {
		// Match all paths except /error
		return
			Predicates.and(
				PathSelectors.regex("/.*"),
				Predicates.not(PathSelectors.regex("/error.*")));
	}


	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}