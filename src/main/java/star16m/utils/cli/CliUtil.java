package star16m.utils.cli;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import star16m.utils.cli.simplecli.SimpleOption;
import star16m.utils.cli.simplecli.SimpleOptions;

public class CliUtil {

    public static String OPTION_REQUIRED_FLAG_STRING = ":";
    
    public static void parseValue(String[] arguments, SimpleOptions simpleOptions) throws Exception {
        if(arguments == null || arguments.length <= 0 || simpleOptions == null || simpleOptions.size() <= 0) {
            throw new IllegalArgumentException();
        }
        List<SimpleOption> simpleOptionList = simpleOptions.getSimpleOptionList();
        Options options = getOptions(simpleOptionList);
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, arguments);
        } catch (ParseException e) {
            throw new Exception (e);
        }
        for (SimpleOption simpleOption : simpleOptionList) {
            boolean specifiedOption = cmd.hasOption(simpleOption.getOpt());
            simpleOption.setSpecified(specifiedOption);
            if (specifiedOption) {
                simpleOption.setOptionValue(cmd.getOptionValue(simpleOption.getOpt()));
            } else {
                if (simpleOption.isRequired()) {
                    throw new Exception("Please specify required option [" + simpleOption.getOpt() + "]");
                }
            }
        }
    }
    public static Options getOptions(List<SimpleOption> simpleOptionList) {
        if (simpleOptionList == null || simpleOptionList.size() <= 0) {
            throw new IllegalArgumentException();
        }
        Options options = new Options();
        for (SimpleOption simpleOption : simpleOptionList) {
            options.addOption(simpleOption.getOpt(), simpleOption.isRequired(), simpleOption.getDescription());
        }
        return options;
    }
}
