package brian.example.eurekafeignclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="eureka-client"
//        , url="http://localhost:9090"
)
public interface GreetingClient {
    @GetMapping("/greeting")
    String greeting(@Nullable @RequestParam("name") String name);
}
