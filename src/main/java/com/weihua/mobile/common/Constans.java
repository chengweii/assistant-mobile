package com.weihua.mobile.common;

import android.os.Environment;

public class Constans {
	public static final String ASSISTANT_ROOT_PATH_NAME = "assistant";

	public static final String ASSISTANT_ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/"
			+ Constans.ASSISTANT_ROOT_PATH_NAME + "/";
}
