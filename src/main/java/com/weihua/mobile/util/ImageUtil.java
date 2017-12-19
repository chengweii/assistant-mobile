package com.weihua.mobile.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.weihua.mobile.common.Constans;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class ImageUtil {

	private static Logger LOGGER = Logger.getLogger(ImageUtil.class);

	public static Bitmap getBitmap(String path) throws Exception {
		if (path != null && FileUtil.isFileExists(Constans.ASSISTANT_IMG_LOCAL_ROOT_PATH + getFileNameFromUrl(path))) {
			Bitmap bitmap = BitmapFactory.decodeFile(Constans.ASSISTANT_IMG_LOCAL_ROOT_PATH + getFileNameFromUrl(path));
			return bitmap;
		}

		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			saveBitmap(bitmap, path);
			inputStream.close();
			return bitmap;
		} else {
			LOGGER.info("Bitmap load failed:" + path);
		}

		return null;
	}

	public static Bitmap fillet(Bitmap bitmap, int roundPx) throws Exception {
		final int width = bitmap.getWidth();
		final int height = bitmap.getHeight();

		Bitmap paintingBoard = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(paintingBoard);
		canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);

		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		final RectF rectF = new RectF(0, 0, width, height);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		final Rect src = new Rect(0, 0, width, height);
		final Rect dst = src;
		canvas.drawBitmap(bitmap, src, dst, paint);
		return paintingBoard;
	}

	private static String getFileNameFromUrl(String url) {
		return url.replaceAll("\\/", "#").replaceAll("\\:", "%").replaceAll("\\?", "$");
	}

	public static void saveBitmap(Bitmap bitmap, String path) throws Exception {
		String filePath = Constans.ASSISTANT_IMG_LOCAL_ROOT_PATH + getFileNameFromUrl(path);
		File file = new File(filePath);
		FileOutputStream out;
		out = new FileOutputStream(file);
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
		out.flush();
		out.close();
	}

}
