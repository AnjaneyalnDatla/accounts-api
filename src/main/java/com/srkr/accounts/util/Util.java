package com.srkr.accounts.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static Date getFormattedDate(Date date) {
		long batch_date = date.getTime();
		Date dt = new Date(batch_date * 1000);

		SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
		return new Date(sfd.format(dt));

	}

}
