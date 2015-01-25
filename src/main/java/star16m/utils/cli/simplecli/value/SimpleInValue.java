package star16m.utils.cli.simplecli.value;

import star16m.utils.array.ArrayUtil;

public class SimpleInValue extends SimpleStringValue {

	private String[] valueInArray;
	
	public SimpleInValue(String[] valueInArray) {
		if (ArrayUtil.isEmpty(valueInArray)) {
			throw new IllegalArgumentException();
		}
		this.valueInArray = valueInArray;
	}
	@Override
	public boolean valueCheck() {
		boolean valueCheck = super.valueCheck();
		String stringValue = (String) getRealValue();
		if (valueCheck) {
			for (String arrayString : valueInArray) {
				if (arrayString.equals(stringValue)) {
					return true;
				}
			}
		}
		return false;
	}

}
