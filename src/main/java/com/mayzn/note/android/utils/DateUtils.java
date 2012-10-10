package com.mayzn.note.android.utils;

import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtils {
	private static final String DATE_FORMAT_RFC3339 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final SimpleDateFormat FORMATTER_RFC3339 = new SimpleDateFormat(DATE_FORMAT_RFC3339);


	private DateUtils() {
		throw new UnsupportedOperationException();
	}

	public static synchronized String format(Date date, String format) {
		return FastDateFormat.getInstance(format).format(date);
	}

	public static synchronized Date parse(String dateString, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	public static synchronized Date parseRfc3339(String formatted) {
		try {
			return FORMATTER_RFC3339.parse(formatted);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static synchronized String formatRfc3339(Date date) {
		return FastDateFormat.getInstance(DATE_FORMAT_RFC3339, TimeZone.getTimeZone("GMT")).
                format(date);
	}
}
