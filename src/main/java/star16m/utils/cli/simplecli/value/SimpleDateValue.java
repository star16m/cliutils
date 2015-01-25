package star16m.utils.cli.simplecli.value;

import java.util.Date;

import star16m.utils.date.DateUtil;
import star16m.utils.string.StringUtil;

public class SimpleDateValue extends SimpleStringValue {

	@Override
	public boolean valueCheck() {
		String valueString = (String) super.getRealValue();
		boolean checkValue = !StringUtil.isEmpty(valueString);
		if (valueString.length() == 8) {
			checkValue &= DateUtil.getYYYYMMDD(valueString) != null;
		} else if (valueString.length() == 14) {
			checkValue &= DateUtil.getYYYYMMDDHHMMSS(valueString) != null;
		} else {
			checkValue = false;
		}
		return checkValue;
	}

	@Override
	public Object getRealValue() {
		Date realValue = null;
		String valueString = (String) super.getRealValue();
		if (valueString.length() == 8) {
			realValue = DateUtil.getYYYYMMDD(valueString);
		} else if (valueString.length() == 14) {
			realValue = DateUtil.getYYYYMMDDHHMMSS(valueString);
		}
		return realValue;
	}
}
