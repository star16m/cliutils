package star16m.utils.cli.simplecli;

public class SimpleOption {

    private String opt;
    private boolean isRequired;
    private String description;
    private boolean specified;
    private String optionValueString;
    
    public SimpleOption(String opt, boolean isRequired, String description) {
        super();
        this.opt = opt;
        this.isRequired = isRequired;
        this.description = description;
    }
    public String getOpt() {
        return opt;
    }
    public boolean isRequired() {
        return isRequired;
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
    public String getOptionValue() {
        return this.optionValueString;
    }
    public void setOptionValue(String optionValue) {
        this.optionValueString = optionValue;
    }
}
