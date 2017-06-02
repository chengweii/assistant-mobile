package com.weihua.mobile.common;

import android.os.Environment;

public class Constans {
	public static final String ASSISTANT_ROOT_PATH_NAME = "assistant";

	public static final String ASSISTANT_IMG_WEB_ROOT_PATH = "https://raw.githubusercontent.com/chengweii/resource/master/img/";

	public static final String ASSISTANT_DATABASE_LOCAL_PATH = Environment.getExternalStorageDirectory().getPath() + "/"
			+ Constans.ASSISTANT_ROOT_PATH_NAME + "/database/assistant.db";

	public static final String ASSISTANT_LOCAL_ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/"
			+ Constans.ASSISTANT_ROOT_PATH_NAME + "/";

	public static final String ASSISTANT_IMG_LOCAL_ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/"
			+ Constans.ASSISTANT_ROOT_PATH_NAME + "/img/";

	public static final int ALARM_SERVICE_ID = 111;

	public static final int FRONT_SERVICE_ID = 888;
}
