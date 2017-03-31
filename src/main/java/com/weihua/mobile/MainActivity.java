package com.weihua.mobile;

import java.io.InputStream;

import com.weihua.common.constant.CommonConstant;
import com.weihua.mobile.util.Log4JUtil;
import com.weihua.mobile.webview.CustomerWebChromeClient;
import com.weihua.ui.userinterface.AssistantInterface;
import com.weihua.util.ExceptionUtil;
import com.weihua.util.TemplateUtil.TemplateReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
	private WebView webView;
	private static String viewFilePath = "index.htm";

	@JavascriptInterface
	public String getResponse(String request, String requestType) {
		String msg = "";
		try {
			AssistantInterface assistantInterface = new AssistantInterface();
			String response = assistantInterface.getResponse(request);
			msg = response;
		} catch (Exception e) {
			msg = ExceptionUtil.getStackTrace(e);
		}
		return msg;
	}

	public void initView() {
		webView = (WebView) findViewById(R.id.wv1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(this, "mainActivity");
		webView.loadUrl("file:///android_asset/" + viewFilePath);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.setWebChromeClient(new CustomerWebChromeClient());
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
	}

	private long exitTime = 0;

	@Override
	public void onBackPressed() {
		if (System.currentTimeMillis() - exitTime > 2000) {
			Toast.makeText(this, "Press once more exit", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			// System.exit(0);
			// android.os.Process.killProcess(android.os.Process.myPid());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.main);

		Log4JUtil.configure();

		MobileReader mobileReader = new MobileReader(this);
		com.weihua.util.TemplateUtil.initTemplateReader(mobileReader);

		initView();
	}

	public static class MobileReader implements TemplateReader {
		private android.content.Context context;

		public MobileReader(android.content.Context context) {
			this.context = context;
		}

		@Override
		public String getTemplateContent(String templateName) {
			String text = "";
			try {
				InputStream input = this.context.getAssets().open(templateName);
				int size = input.available();
				byte[] buffer = new byte[size];
				input.read(buffer);
				input.close();
				text = new String(buffer, CommonConstant.CHARSET_UTF8);
			} catch (Exception e) {
				text = ExceptionUtil.getStackTrace(e);
			}
			return text;
		}
	}

}
