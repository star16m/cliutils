package star16m.utils.cli.simplecli;

import java.util.ArrayList;
import java.util.List;

import star16m.utils.cli.ValueType;
import star16m.utils.cli.simplecli.value.SimpleDateValue;
import star16m.utils.cli.simplecli.value.SimpleFileValue;
import star16m.utils.cli.simplecli.value.SimpleInValue;
import star16m.utils.cli.simplecli.value.SimpleStringValue;

public class SimpleOptions {

	private boolean parsed;
    private List<SimpleOption> simpleOptionList;
    public SimpleOptions() {
        this.simpleOptionList = new ArrayList<SimpleOption>();
        this.parsed = false;
    }
    public SimpleOptions append(String opt, boolean isRequired, boolean hasArgument, String description) {
    	return this.append(opt, isRequired, hasArgument, description, ValueType.STRING, null);
    }
    public SimpleOptions append(String opt, boolean isRequired, boolean hasArgument, String description, ValueType valueType) {
    	return this.append(opt, isRequired, hasArgument, description, valueType, null);
    }
    public SimpleOptions append(String opt, boolean isRequired, boolean hasArgument, String description, String[] stringArray) {
    	return this.append(opt, isRequired, hasArgument, description, ValueType.INSTRING, stringArray);
    }
    public SimpleOptions append(String opt, boolean isRequired, boolean hasArgument, String description, ValueType valueType, String[] stringArray) {
    	SimpleStringValue simpleValue = null;
    	switch (valueType) {
    	case STRING:
    		simpleValue = new SimpleStringValue();
    		break;
    	case DATE:
    		simpleValue = new SimpleDateValue();
    		break;
    	case FILE:
    		simpleValue = new SimpleFileValue();
    		break;
    	case INSTRING:
    		simpleValue = new SimpleInValue(stringArray);
    		break;
    	default:
    		throw new IllegalArgumentException();
    	}
        this.simpleOptionList.add(new SimpleOption(opt, isRequired, hasArgument, description, simpleValue));
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
    
    public Object getOptionValue(String opt) {
        SimpleOption simpleOption = getOption(opt);
        return simpleOption.getOptionValue().getRealValue();
    }
    
    public SimpleOption getOption(String opt) {
        for (SimpleOption simpleOption : this.simpleOptionList) {
            if (simpleOption.getOpt().equals(opt)) {
                return simpleOption;
            }
        }
        return null;
    }
    public boolean parsed() {
    	return parsed;
    }
    public void setParsed() {
    	this.parsed = true;
    }
    
}
