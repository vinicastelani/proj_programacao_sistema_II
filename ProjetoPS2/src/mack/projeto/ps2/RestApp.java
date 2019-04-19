package mack.projeto.ps2;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class RestApp extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new RestApp().run(new String[]{"server"});
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        environment.jersey().register(new TimeDAO());
    }
}
