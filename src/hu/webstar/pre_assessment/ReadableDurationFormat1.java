package hu.webstar.pre_assessment;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReadableDurationFormat1 {

	Map<String, Long> conversions = new LinkedHashMap<>();
	{
		conversions.put("year", 31_536_000L);
		conversions.put("day", 86400L);
		conversions.put("hour", 3600L);
		conversions.put("minute", 60L);
		conversions.put("second", 1L);
	}

	public static void main(String[] args) {
		new ReadableDurationFormat1().run();
	}

	private void run() {
		System.out.println(getReadableDuration(123456789));
	}

	private String getReadableDuration(long durationSec) {
		if (durationSec <= 0) {
			return durationSec == 0 ? "now" : null;
		}
		var result = new StringBuilder();
		for (Map.Entry<String, Long> entry : conversions.entrySet()) {
			long numUnit = durationSec / entry.getValue();
			if (numUnit > 0) {
				String fullUnit = "%d %s%s".formatted(numUnit, entry.getKey(), numUnit > 1 ? "s" : "");
				result.append(result.length() > 0 ? ", " : "").append(fullUnit);
			}
			durationSec %= entry.getValue();
		}
		int i = result.lastIndexOf(",");
		return i > -1 ? result.replace(i, i + 1, " and").toString() : result.toString();
	}

}
