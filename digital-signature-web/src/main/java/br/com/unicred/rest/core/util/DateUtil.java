package br.com.unicred.rest.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String format(Date data, String formato) {

		try {

			SimpleDateFormat formatter = new SimpleDateFormat(formato);

			String formatted = formatter.format(data);

			return formatted;

		} catch (Exception e) {
			return null;
		}

	}
	
}