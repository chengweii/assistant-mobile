package com.weihua.mobile.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FileUtil {

	/**
	 * 写入文件内容
	 * 
	 * @param content
	 * @param filePath
	 * @throws Exception
	 */
	public static void writeFileContent(String content, String filePath) throws Exception {
		File file = new File(filePath);
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file, false);
		out.write(content.getBytes("utf-8"));
		out.close();
	}

	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String getFileContent(String filePath) throws Exception {
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		InputStreamReader streamReader = new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8");
		BufferedReader reader = new BufferedReader(streamReader);
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		reader.close();
		return stringBuilder.toString();
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param filePath
	 */
	public static boolean isFileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

}
