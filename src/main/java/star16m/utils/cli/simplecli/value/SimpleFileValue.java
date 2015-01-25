package star16m.utils.cli.simplecli.value;

import java.io.File;

import star16m.utils.string.StringUtil;

public class SimpleFileValue extends SimpleStringValue {

	@Override
	public boolean valueCheck() {
		String valueString = (String)super.getRealValue();
		if (!StringUtil.isEmpty(valueString)) {
			return new File(valueString).exists();
		} else {
			return false;
		}
	}

	@Override
	public Object getRealValue() {
		return new File((String)super.getRealValue());
	}
}
