package star16m.utils.cli.simplecli;

import star16m.utils.cli.simplecli.value.SimpleStringValue;

public class SimpleOption {

    private String opt;
    private boolean isRequired;
    private boolean hasArgument;
    private String description;
    private boolean specified;
    private SimpleStringValue optionValue;
    
    public SimpleOption(String opt, boolean isRequired, boolean hasArgument, String description, SimpleStringValue optionValue) {
        super();
        this.opt = opt;
        this.isRequired = isRequired;
        this.hasArgument = hasArgument;
        this.description = description;
        this.optionValue = optionValue;
    }
    public String getOpt() {
        return opt;
    }
    public boolean isRequired() {
        return isRequired;
    }
    public boolean hasArgument() {
    	return this.hasArgument;
    }
    public String getDescription() {
        return description;
    }
    public boolean isSpecified() {
        return this.specified;
    }
    public void setSpecified(boolean specified) {
        this.specified = specified;
    }
    public SimpleStringValue getOptionValue() {
        return this.optionValue;
    }
    public void setOptionValue(String optionValueString) {
        this.optionValue.setValue(optionValueString);
    }
}
