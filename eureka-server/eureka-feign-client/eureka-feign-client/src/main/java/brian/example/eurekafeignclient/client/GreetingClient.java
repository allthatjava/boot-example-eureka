package brian.example.eurekafeignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client", fallback = GreetingClientFailoverImpl.class)
public interface GreetingClient {
    @GetMapping("/greeting")
    String greeting();
}
