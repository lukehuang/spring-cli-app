package org.coderearth.springcliapp;

import org.coderearth.springcliapp.command.CliCommandManager;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        if (args.length == 0) {
            new CliCommandManager().printCliHelp();
        } else
            new SpringApplicationBuilder(Application.class)
                    .bannerMode(Banner.Mode.OFF)
                    .logStartupInfo(false)
                    .registerShutdownHook(false)
                    .build()
                    .run(args)
                    .close();
    }

}