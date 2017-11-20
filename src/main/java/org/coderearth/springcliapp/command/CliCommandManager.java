package org.coderearth.springcliapp.command;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class CliCommandManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CliCommandManager.class);

    private final Options options;
    private final HelpFormatter helpFormatter;

    public CliCommandManager() {
        options = new Options();

        options.addOption(new Option("h", "help", false, "print this message"));

        options.addOption(
                Option
                        .builder("u")
                        .argName("u")
                        .longOpt("username")
                        .desc("registered username to access the application")
                        .hasArg(true)
                        .numberOfArgs(1)
                        .required(true)
                        .type(String.class)
                        .build()
        );

        options.addOption(
                Option
                        .builder("p")
                        .argName("p")
                        .longOpt("password")
                        .desc("password combination for given username")
                        .hasArg(true)
                        .numberOfArgs(1)
                        .required(true)
                        .type(String.class)
                        .build()
        );

        options.addOption(
                Option
                        .builder("y")
                        .argName("y")
                        .longOpt("year")
                        .desc("year for which data is requested")
                        .hasArg(true)
                        .numberOfArgs(1)
                        .required(true)
                        .type(Integer.TYPE)
                        .build()
        );
        helpFormatter = new HelpFormatter();
    }

    public Options getOptions() {
        return this.options;
    }

    public void printCliHelp() {
        this.helpFormatter.printHelp("java -jar spring-cli-app-<version>.jar --username=<username> --password=<key> --year=<year>", this.options);
    }

    /**
     * SRP says <code>parse</code> method should do exactly one and only one thing which is parse arguments and convert into
     * <code>Command</code> which can be used by core application - it should not make System.exit() or some nasty thing
     * with application work-flow itself. Because thats the responsibility of caller of this method and that will be
     * notified by throwing <code>ParseException</code>
     *
     * @param args
     * @return
     * @throws ParseException
     */
    public Command parse(String[] args) throws ParseException {
        final CommandLineParser commandLineParser = new DefaultParser();

        try {
            final CommandLine commandLine = commandLineParser.parse(this.getOptions(), args, false);
            return Command
                    .builder()
                    .username(commandLine.getOptionValue("username"))
                    .password(commandLine.getOptionValue("password"))
                    .year(Year.of(parseYear(commandLine.getOptionValue("year"))))
                    .build();
        } catch (ParseException e) {
            throw e;
        }
    }

    private Integer parseYear(String year) throws ParseException {
        try {
            return Integer.parseInt(year);
        } catch (NumberFormatException nfe) {
            throw new ParseException(nfe.getMessage());
        }
    }

}