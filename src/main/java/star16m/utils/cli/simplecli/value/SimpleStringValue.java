package star16m.utils.cli.simplecli.value;

import star16m.utils.string.StringUtil;

public class SimpleStringValue {

	private String valueString;

	public boolean valueCheck() {
		return !StringUtil.isEmpty(valueString);
	}

	public Object getRealValue() {
		return this.valueString;
	}
	public void setValue(Object object) {
		this.valueString = object.toString();
	}
}
