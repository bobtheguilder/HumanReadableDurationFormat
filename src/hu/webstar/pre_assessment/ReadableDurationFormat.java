package hu.webstar.pre_assessment;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReadableDurationFormat {

	Map<String, Long> conversions = new LinkedHashMap<>();
	{
		conversions.put("year", 31_536_000L);
		conversions.put("day", 86400L);
		conversions.put("hour", 3600L);
		conversions.put("minute", 60L);
	}

	public static void main(String[] args) {
		new ReadableDurationFormat().run();
	}

	private void run() {
		System.out.println(getReadableDuration(123456789));
	}

	private String getReadableDuration(long durationSec) {
		if (durationSec <= 0) {
			return durationSec == 0 ? "now" : null;
		}
		Map<String, Long> durationUnits = new LinkedHashMap<>();
		for (Map.Entry<String, Long> entry : conversions.entrySet()) {
			durationUnits.put(entry.getKey(), durationSec / entry.getValue());
			durationSec %= entry.getValue();
		}
		durationUnits.put("second", durationSec);
		return format(durationUnits);
	}

	private String format(Map<String, Long> durationUnits) {
		var result = new StringBuilder();
		for (Map.Entry<String, Long> entry : durationUnits.entrySet()) {
			var unit = entry.getKey();
			var value = entry.getValue();
			result.append(result.length() > 0 && value > 0 ? ", " : "").append(value > 0 ? value + " " + unit : "").append(value > 1 ? "s" : "");
		}
		int i = result.lastIndexOf(",");
		return i > -1 ? result.replace(i, i + 1, " and").toString() : result.toString();
	}

}
