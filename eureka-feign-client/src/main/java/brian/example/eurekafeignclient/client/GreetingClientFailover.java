package brian.example.eurekafeignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="eureka-client-failover")
public interface GreetingClientFailover {

    @GetMapping("/greeting-failover")
    String greeting();
}
