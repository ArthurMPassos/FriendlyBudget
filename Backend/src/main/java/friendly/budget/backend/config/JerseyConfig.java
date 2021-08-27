package friendly.budget.backend.config;

import friendly.budget.backend.resource.Requests;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(Requests.class);
    }
}
