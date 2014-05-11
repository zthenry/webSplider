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
public interface DownLoadService {

	/**
	 * 获取文件二进制
	 * @param url
	 * @return
	 */
	byte[] getFileFromNetByUrl(String strUrl);
	
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
	void writeFileToDisk(byte[] img, String fileName);
}
