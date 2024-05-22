package brian.example.eurekaclientfailover;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class EurekaClientFailoverApplication {
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/greeting-failover")
    public String serviceInstancesByApplicationName(@Nullable @RequestParam("name") String name) {
        return String.format("Hello from  %s failover %s", eurekaClient.getApplication(appName).getName(), name);
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientFailoverApplication.class, args);
    }
}
