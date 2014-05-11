/**
 * 
 */
package com.cyou.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cyou.service.DownLoadService;

/**
 * @author root
 * 
 */
public class DownLoadImageServiceImpl implements DownLoadService {

	@Override
	public byte[] getFileFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
			byte[] btImg = readInputStream(inStream);
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] readInputStream(InputStream inStream) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	@Override
	public void writeFileToDisk(byte[] img, String fileName) {
		try {
			File file = new File(fileName);
			FileOutputStream fops = new FileOutputStream(file);
			fops.write(img);
			fops.flush();
			fops.close();
			System.out.println("文件"+fileName+"已经保存");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
