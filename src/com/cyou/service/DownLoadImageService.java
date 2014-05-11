/**
 * zhangtao
 * 2014.5.11
 * 图片下载接口
 */
package com.cyou.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangtao
 *
 */
public interface DownLoadImageService {

	/**
	 * 获取图片二进制
	 * @param url
	 * @return
	 */
	byte[] getImageFromNetByUrl(String strUrl);
	
	/**
	 * 将文件输入流转换为二进制文件
	 * @param inStream
	 * @return
	 * @throws IOException 
	 */
	byte[] readInputStream(InputStream inStream) throws IOException;
	
	/**
	 * 将文件保存到本地
	 * @param img
	 * @param fileName
	 */
	void writeImageToDisk(byte[] img, String fileName);
}
