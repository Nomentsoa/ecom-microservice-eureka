package ca.lazanomentsoa.gateway;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator custumRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("product-service", r -> r
                        .path("/api/products/**")
                        .filters(f -> f.circuitBreaker(config -> config.setName("ecomBreaker").setFallbackUri("forward:/fallback/products")))
                        //.filters(f -> f.rewritePath("/products(?<segment>/?.*)", "/api/products${segment}"))
                        .uri("lb://PRODUCT-SERVICE")
                )
                .route( "user-service", r -> r
                        .path("/users/**")
                        .filters(f -> f.rewritePath("/users(?<segment>/?.*)", "/api/users${segment}"))
                        .uri("lb://USER-SERVICE")
                )
                .route( "eureka-service", r -> r
                        .path("/eureka/main")
                        .filters(f -> f.rewritePath("/eureka/main","/"))
                        .uri("http://localhost:8761")
                )
                .route( "eureka-service-static", r -> r
                        .path("/eureka/**")
                        .uri("http://localhost:8761")

                ).build();
    }
}
