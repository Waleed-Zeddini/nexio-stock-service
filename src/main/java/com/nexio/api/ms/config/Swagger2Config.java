package com.nexio.api.ms.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationCodeGrant;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Value("${info.app.name}")
	private String serviceName;
	
	@Value("${info.app.desc}")
	private String serviceDesc;
	
	@Value("${info.app.contact.name}")
	private String contactName;
	
	@Value("${info.app.contact.website}")
	private String contactWebsite;
	
	@Value("${info.app.contact.email}")
	private String contactEmail;
	
	@Value("${info.app.license}")
	private String license;
	
	@Value("${info.app.license-url}")
	private String licenseUrl;
	
	@Value("${info.app.version}")
	private String version;
	
//	@Value("${uaa.clientId}")
//	String clientId;
//	@Value("${uaa.clientSecret}")
//	String clientSecret;
//	
//	@Value("${uaa.url}")
//	String oAuthServerUri;

	  
	    @Bean
	    public Docket buildApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .paths(regex("/api.*"))
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build()
//	                .securitySchemes(Collections.singletonList(oauth()))
	                .apiInfo(apiEndPointsInfo());
	    }

	   private ApiInfo apiEndPointsInfo() {
	        return new ApiInfoBuilder()
	        		.title(serviceName)
	                .description(serviceDesc)
	                .contact(new Contact(contactName, contactWebsite, 
	                		contactEmail))
	                .license(license)
	                .licenseUrl(licenseUrl)
	                .version(version)
	                .build();
	    }

	 
	
//	@Bean
//	List<GrantType> grantTypes() {
//		List<GrantType> grantTypes = new ArrayList<>();
//		TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint(oAuthServerUri+"/oauth/authorize", clientId, clientSecret );
//        TokenEndpoint tokenEndpoint = new TokenEndpoint(oAuthServerUri+"/oauth/token", "token");
//        grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
//        return grantTypes;
//	}
//	
//	@Bean
//    SecurityScheme oauth() {
//        return new OAuthBuilder()
//                .name("OAuth2")
//                .scopes(scopes())
//                .grantTypes(grantTypes())
//                .build();
//    }
//	
//	private List<AuthorizationScope> scopes() {
//		List<AuthorizationScope> list = new ArrayList<>();
//		list.add(new AuthorizationScope("read_scope","Grants read access"));
//		list.add(new AuthorizationScope("write_scope","Grants write access"));
//		list.add(new AuthorizationScope("admin_scope","Grants read write and delete access"));
//		return list;
//    }	
//
//	@SuppressWarnings("deprecation")
//	@Bean
//    public SecurityConfiguration securityInfo() {
//        return new SecurityConfiguration(clientId, clientSecret, "realm", clientId, "apiKey", ApiKeyVehicle.HEADER, "api_key", "");
//    }
//	
//    /**
//    *
//    * @return ApiInf
//    */
//   @SuppressWarnings("unused")
//private ApiInfo apiInfo() {
//       return new ApiInfoBuilder().title("Order Microservice API").description("Microservice Order")
//               .termsOfServiceUrl("https://www.zeddini.com/api")
//               .contact(new Contact("Walid ZEDDINI", "https://zeddini.com/", "walid.zeddini@gmail.com"))
//               .license("ZED GPL GNU - Personnalized")
//               .licenseUrl("\"https://www.zeddini.com/ZPL-LICENSE-1-2")
//               .version("1.0.0")
//               .build();
//
//   }
	
}