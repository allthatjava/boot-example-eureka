### Eureka + API Gateway + Feign Client + Resilience4j
- eureka-server : Euraka Server
- example-spring-gateway : API Gateway
- eureka-feign-client : Main application that uses Feign Client
- eureka-client : Microservice that returns Greeting message with given name
- eureka-client-failover : Microservice that takes over for eureka-client failure

### Flow
* `localhost:8080/get-greeting?name=brian` will access `eureka-feign-client` service
* Then it will use `eureka-server` loadbalance address to get access to microservices `eureka-client`
* Since Feign Client is used with loadbalancer feature by service name, API Gateway is not totally necessary
* If you want to use API Gateway, uncomment the following `url` attribute on @FeignClient annotation

```java
@FeignClient(value="eureka-client"
//        , url="http://localhost:9090"
)
public interface GreetingClient{...}
```

### Failover
When `eureka-feign-client` try to connect to `eureka-client` if that service is off or throws any errors, `resilience4j` will take detour to `eureka-client-failover` by feign client


