package com.weihua.mobile.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.reflect.TypeToken;
import com.weihua.assistant.entity.request.BaseRequest;
import com.weihua.mobile.common.Constans;
import com.weihua.mobile.util.dbhelper.MobileDBHelper;
import com.weihua.ui.userinterface.AssistantInterface;
import com.weihua.ui.userinterface.UserInterface;
import com.weihua.util.GsonUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmService extends BroadcastReceiver {

	private static Logger LOGGER = Logger.getLogger(AlarmService.class);

	private static final UserInterface USER_INTERFACE = new AssistantInterface();
	private static final String ALARM_REQUEST_CONTENT;

	private static MobileDBHelper mobileDBHelper = null;

	static {
		Log4JUtil.configure();
		BaseRequest.RequestData request = new BaseRequest.RequestData();
		request.isLocationPath = true;
		request.requestContent = "callAlarmService";
		ALARM_REQUEST_CONTENT = GsonUtil.toJson(request);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if (mobileDBHelper == null) {
				mobileDBHelper = new MobileDBHelper(context, Constans.ASSISTANT_DATABASE_LOCAL_PATH, 1);
				com.weihua.util.DBUtil.initDBHelper(mobileDBHelper);
			}
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_USER_PRESENT)) {
				String responseContent = USER_INTERFACE.getResponse(ALARM_REQUEST_CONTENT);
				Map<String, Object> alarm = GsonUtil.getEntityFromJson(responseContent,
						new TypeToken<Map<String, Object>>() {
						});
				if (alarm != null && alarm.get("msgList") != null) {
					List<Map<String, String>> alarmList = GsonUtil.getEntityFromJson(alarm.get("msgList").toString(),
							new TypeToken<List<Map<String, String>>>() {
							});
					for (Map<String, String> item : alarmList) {
						showMsg(context, item);
					}
				}
			} else if (action.equals(Intent.ACTION_SCREEN_ON)) {
			} else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			}
			Map<String, String> item = new HashMap<String, String>();
			item.put("content", "content");
			item.put("title", "title");
			item.put("icon",
					"http://www.jlonline.com/d/file/shehuixinwen/20170517/23feb34bc536583ccca35ae76a17e37f.jpg");
			showMsg(context, item);
		} catch (Exception e) {
			LOGGER.error("AlarmService run error:", e);
		}
	}

	private void showMsg(final Context context, final Map<String, String> msg) throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					NotificationUtil.showNotification(context, msg.get("content"), msg.get("title"), msg.get("content"),
							null, null, msg.get("icon"), (int) (Math.random() * 1000) + 1000, null);
				} catch (Exception e) {
					LOGGER.error("ShowNotification error:", e);
				}
			}
		}).start();
	}

}
