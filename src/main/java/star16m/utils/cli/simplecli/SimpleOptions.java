package star16m.utils.cli.simplecli;

import java.util.ArrayList;
import java.util.List;

public class SimpleOptions {

    private List<SimpleOption> simpleOptionList;
    public SimpleOptions() {
        this.simpleOptionList = new ArrayList<SimpleOption>();
    }
    public SimpleOptions append(String opt, boolean isRequired, String description) {
        this.simpleOptionList.add(new SimpleOption(opt, isRequired, description));
        return this;
    }
    
    public SimpleOption[] getSimpleOptionArray() {
        return this.simpleOptionList.toArray(new SimpleOption[0]);
    }
    
    public List<SimpleOption> getSimpleOptionList() {
        return this.simpleOptionList;
    }
    
    public int size() {
        return this.simpleOptionList.size();
    }
    
    public boolean isRequiredOption(String opt) {
        return this.simpleOptionList.contains(opt);
    }
    
    public boolean isSpecifiedOption(String opt) {
        SimpleOption simpleOption = getOption(opt);
        return simpleOption.isSpecified();
    }
    
    public String getOptionValue(String opt) {
        SimpleOption simpleOption = getOption(opt);
        return simpleOption.getOptionValue();
    }
    
    public SimpleOption getOption(String opt) {
        for (SimpleOption simpleOption : this.simpleOptionList) {
            if (simpleOption.getOpt().equals(opt)) {
                return simpleOption;
            }
        }
        return null;
    }
    
}
