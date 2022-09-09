package brian.example.eurekaclientfailover;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailoverController {
    @Autowired
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/greeting-failover")
    public String serviceInstancesByApplicationName() {
        return String.format("Hello from  %s failover", eurekaClient.getApplication(appName).getName());
    }
}
