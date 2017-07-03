package com.weihua.mobile.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.weihua.util.ExceptionUtil;

import android.content.Context;

public class PropertiesUtil {
	private static Logger LOGGER = Logger.getLogger(PropertiesUtil.class);

	public static Properties getProperties(Context context) {
		Properties props = new Properties();
		try {
			InputStream in = context.getAssets().open("verification.properties");
			props.load(in);
		} catch (Exception e) {
			ExceptionUtil.propagate(LOGGER, e);
		}
		return props;
	}
}
