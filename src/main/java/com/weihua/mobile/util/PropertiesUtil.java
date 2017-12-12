package com.weihua.mobile.util;

import java.io.InputStream;
import java.util.Properties;

import com.google.common.base.Throwables;

import android.content.Context;

public class PropertiesUtil {
	public static Properties getProperties(Context context,String fileName) {
		Properties props = new Properties();
		try {
			InputStream in = context.getAssets().open(fileName);
			props.load(in);
		} catch (Exception e) {
			Throwables.propagate(e);
		}
		return props;
	}
}
