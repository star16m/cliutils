package star16m.utils.cli;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import star16m.utils.array.ArrayUtil;
import star16m.utils.cli.simplecli.SimpleOption;
import star16m.utils.cli.simplecli.SimpleOptions;

public class CliUtil {

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
        if(ArrayUtil.isEmpty(arguments) || simpleOptions == null || simpleOptions.size() <= 0) {
        	usage("Please specify option.", simpleOptions);
        	return false;
        }
        List<SimpleOption> simpleOptionList = simpleOptions.getSimpleOptionSet();
        Options options = simpleOptions.getOptions();
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, arguments);
        } catch (ParseException e) {
        	usage("Unexpected option.", simpleOptions);
        	return false;
        }
        for (SimpleOption simpleOption : simpleOptionList) {
            boolean specifiedOption = cmd.hasOption(simpleOption.getOpt());
            simpleOption.setSpecified(specifiedOption);
            if (specifiedOption) {
                simpleOption.setOptionValue(cmd.getOptionValue(simpleOption.getOpt()));
                if (!simpleOption.getOptionValue().valueCheck()) {
                	usage("Unexpected option value.[" + simpleOption.getOptionValue().getRealValue() + "]", simpleOptions);
                	return false;
                }
            } else {
                if (simpleOption.isRequired()) {
                	usage("Target option is required.", simpleOptions);
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
    	formatter.printHelp(message, simpleOptions.getOptions());
    }
    
}
