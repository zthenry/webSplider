/**
 * 
 */
package com.cyou.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author root
 *
 */
public class BaiduProperty {
	
//		private static final Logger LOG = Logger.getLogger(AP.class);
		private static  String propertyUrl = "/resource/baidu.properties";
		private static Properties property;
	   
		static{
			InputStream is = null;
			is = BaiduProperty.class.getResourceAsStream(propertyUrl);
			property = new Properties();
			try {
				property.load(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				LOG.debug(e.getStackTrace());
			}
		}
		
		/**
	     * Searches for the property with the specified key in this property list.
	     * If the key is not found in this property list, the default property list,
	     * and its defaults, recursively, are then checked. The method returns
	     * <code>null</code> if the property is not found.
	     *
	     * @param   key   the property key.
	     * @return  the value in this property list with the specified key value.
	     * @see     #setProperty
	     * @see     #defaults
	     */
	    public static String getProperty(String key) {
	    	try {
				return new String(property.getProperty(key).getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
	    }
	    /**
	     * Searches for the property with the specified key in this property list.
	     * If the key is not found in this property list, the default property list,
	     * and its defaults, recursively, are then checked. The method returns the
	     * default value argument if the property is not found.
	     *
	     * @param   key            the hashtable key.
	     * @param   defaultValue   a default value.
	     *
	     * @return  the value in this property list with the specified key value.
	     * @see     #setProperty
	     * @see     #defaults
	     */
	    public static String getProperty(String key, String defaultValue) {
	    	return property.getProperty(key);
	    }
	
}
