package brian.example.eurekafeignclient.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GreetingClientFailoverImpl implements GreetingClient{

    @Autowired
    GreetingClientFailover failover;

    @Override
    public String greeting() {
        return failover.greeting();
    }
}
