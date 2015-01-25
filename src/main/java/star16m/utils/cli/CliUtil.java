package star16m.utils.cli;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import star16m.utils.cli.simplecli.SimpleOption;
import star16m.utils.cli.simplecli.SimpleOptions;

public class CliUtil {

	private static Options getOptions(SimpleOptions simpleOptions) {
		List<SimpleOption> simpleOptionList = simpleOptions.getSimpleOptionList();
		if (simpleOptionList == null || simpleOptionList.size() <= 0) {
			throw new IllegalArgumentException();
		}
		Options options = new Options();
		Option option = null;
		for (SimpleOption simpleOption : simpleOptionList) {
			options.addOption(simpleOption.getOpt(), simpleOption.hasArgument(), simpleOption.getDescription());
			option = options.getOption(simpleOption.getOpt());
			option.setRequired(simpleOption.isRequired());
		}
		return options;
	}
	public static SimpleOptions getOptions(String optionString, boolean isRequired, boolean hasArgument, String description) {
		return getOptions(optionString, isRequired, hasArgument,description, ValueType.STRING, null);
	}
    public static SimpleOptions getOptions(String optionString, boolean isRequired, boolean hasArgument, String description, String[] stringArray) {
    	return getOptions(optionString, isRequired, hasArgument, description, ValueType.INSTRING, stringArray);
    }
    public static SimpleOptions getOptions(String optionString, boolean isRequired, boolean hasArgument, String description, ValueType valueType) {
    	return getOptions(optionString, isRequired, hasArgument, description, valueType, null);
    }
    public static SimpleOptions getOptions(String optionString, boolean isRequired, boolean hasArgument, String description, ValueType valueType, String[] stringArray) {
    	SimpleOptions options = new SimpleOptions();
		return options.append(optionString, isRequired, hasArgument,description, valueType, stringArray);
    }
    public static boolean parseValue(SimpleOptions simpleOptions, String[] arguments) throws Exception {
        if(arguments == null || arguments.length <= 0 || simpleOptions == null || simpleOptions.size() <= 0) {
        	usage("filefinder", simpleOptions);
        	return false;
        }
        List<SimpleOption> simpleOptionList = simpleOptions.getSimpleOptionList();
        Options options = getOptions(simpleOptions);
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, arguments);
        } catch (ParseException e) {
        	usage("filefinder", simpleOptions);
        	return false;
        }
        for (SimpleOption simpleOption : simpleOptionList) {
            boolean specifiedOption = cmd.hasOption(simpleOption.getOpt());
            simpleOption.setSpecified(specifiedOption);
            if (specifiedOption) {
                simpleOption.setOptionValue(cmd.getOptionValue(simpleOption.getOpt()));
                if (!simpleOption.getOptionValue().valueCheck()) {
                	usage("unexpected value.[" + simpleOption.getOptionValue().getRealValue() + "]", simpleOptions);
                	return false;
                }
            } else {
                if (simpleOption.isRequired()) {
                	usage("target option is required.", simpleOptions);
                	return false;
                }
            }
            
        }
        
        simpleOptions.setParsed();
        return true;
    }
    public Object getOptionValue(SimpleOptions simpleOptions, String opt) {
    	if (!simpleOptions.parsed()) {
    		throw new RuntimeException("Please parse arguments.");
    	}
        SimpleOption simpleOption = simpleOptions.getOption(opt);
        return simpleOption.getOptionValue().getRealValue();
    }
    public static void usage(String message, SimpleOptions simpleOptions) {
    	HelpFormatter formatter = new HelpFormatter();
    	formatter.printHelp(message, getOptions(simpleOptions));
    }
    
}
