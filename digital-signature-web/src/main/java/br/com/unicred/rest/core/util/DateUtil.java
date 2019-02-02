package br.com.unicred.rest.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String format(final Date data, final String formato) {

		try {

			SimpleDateFormat formatter = new SimpleDateFormat(formato);

			String formatted = formatter.format(data);

			return formatted;

		} catch (final Exception e) {
			return null;
		}

	}
	
}
