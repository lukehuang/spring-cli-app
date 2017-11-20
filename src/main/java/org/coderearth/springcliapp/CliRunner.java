package org.coderearth.springcliapp;

import org.apache.commons.cli.ParseException;
import org.coderearth.springcliapp.command.CliCommandManager;
import org.coderearth.springcliapp.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by kunal_patel on 20/11/17.
 */
@Component
public class CliRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CliRunner.class);

    @Autowired
    private CliCommandManager cliCommandManager;

    @Override
    public void run(String... args) throws Exception {
        try {
            final Command command = cliCommandManager.parse(args);
            LOGGER.info("{}", command);
        } catch (ParseException pe) {
            cliCommandManager.printCliHelp();
            LOGGER.error("Error occuered while parsing CLI arguments, " + pe.getMessage());
            System.exit(1);
        }
    }
}
