package com.weihua.mobile.util;

import java.util.Map;

import org.apache.log4j.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmService extends BroadcastReceiver {

	private static Logger LOGGER = Logger.getLogger(AlarmService.class);

	static {
		Log4JUtil.configure();
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_USER_PRESENT)) {
			} else if (action.equals(Intent.ACTION_SCREEN_ON)) {
			} else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			}

			// Map<String, String> item = new HashMap<String, String>();
			// item.put("content", "content");
			// item.put("title", "title");
			// item.put("icon","http://www.jlonline.com/d/file/shehuixinwen/20170517/23feb34bc536583ccca35ae76a17e37f.jpg");
			// showMsg(context, item);

		} catch (Exception e) {
			LOGGER.error("AlarmService run error:", e);
		}
	}

	private void showMsg(final Context context, final Map<String, String> msg) throws Exception {
		if (msg != null && msg.get("title") != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						NotificationUtil.showNotification(context, msg.get("content"), msg.get("title"),
								msg.get("content"), null, null, msg.get("icon"), (int) (Math.random() * 1000) + 1000,
								null);
					} catch (Exception e) {
						LOGGER.error("ShowNotification error:", e);
					}
				}
			}).start();
		}
	}

}
