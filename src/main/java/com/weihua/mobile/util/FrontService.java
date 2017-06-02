package com.weihua.mobile.util;


import org.apache.log4j.Logger;

import com.weihua.mobile.common.Constans;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FrontService extends Service {

	private static Logger LOGGER = Logger.getLogger(FrontService.class);

	@Override
	public void onCreate() {
		Log4JUtil.configure();
		super.onCreate();

		setForegroundService("Master,I am at your service.^_^", "Master,I am at your service.^_^",
				"Please keep me here with you.⊙﹏⊙ ");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return 0;
	}

	private void setForegroundService(String ticker, String title, String text) {
		try {
			Notification notification = NotificationUtil.showNotification(this, ticker, title, text, null, null, null,
					Constans.FRONT_SERVICE_ID, null);
			startForeground(Constans.FRONT_SERVICE_ID, notification);
		} catch (Exception e) {
			LOGGER.error("FrontService started failed:", e);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}