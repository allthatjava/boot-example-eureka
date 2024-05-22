package brian.example.eurekafeignclient;

import brian.example.eurekafeignclient.client.GreetingClient;
import brian.example.eurekafeignclient.client.GreetingClientFailover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@EnableFeignClients
@Controller
public class EurekaFeignClientApplication {

    @Autowired
    private GreetingClient greetingClient;
    @Autowired
    private GreetingClientFailover greetingClientFailover;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignClientApplication.class, args);
    }

    @GetMapping("/get-greeting")
    public String greeting(Model model, @Nullable @RequestParam("name") String name){
        model.addAttribute("greeting", getGreetingFromGreetingClient(name));
        return "greeting-view";
    }

    public String getGreetingFromGreetingClient(String name){
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        return circuitBreaker.run(
                ()->greetingClient.greeting(name),  // Normal case
                throwable -> getGreetingFromGreetingClientFailover(name, throwable) // Failover case
        );
    }

    public String getGreetingFromGreetingClientFailover(String name, Throwable throwable){
        System.out.println("Fallback method called:"+throwable.getMessage());
        return greetingClientFailover.greeting(name);
    }

}
