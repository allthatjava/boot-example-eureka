package brian.example.spring.gateway.examplespringgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaGatewayConfig {

    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/greeting")
                        .uri("lb://eureka-client")
                        .id("eureka-client"))
                .route(r -> r.path("/greeting-failover")
                        .uri("lb://eureka-client-failover")
                        .id("eureka-client-failover"))
                .build();
    }

}
