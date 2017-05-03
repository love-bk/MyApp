package gjj.com.myapp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import gjj.com.myapp.MyApplication;

public class SPUtil {

	/**
	 * 保存登陆账号信息
	 * @param username
	 * @param password
	 */
	public static void saveLoginToSP(Context ct,String username,String password,long tutorId) {
		SharedPreferences setting =ct.getSharedPreferences("myInfo", ct.MODE_PRIVATE);
		Editor editor = setting.edit();
		editor.putString(Constants.USERNAME,username);
		editor.putString(Constants.PASSWORD,password);
		editor.putLong(Constants.TUTORID,tutorId);
		editor.apply();
	}

	/**
	 * 获取登录账号信息
	 */
	public static boolean getLoginInfoFromSP(Context ct) {
		SharedPreferences setting = ct.getSharedPreferences("myInfo", ct.MODE_PRIVATE);
		String username = setting.getString(Constants.USERNAME, null);
		String password = setting.getString(Constants.PASSWORD, null);
		if (username != null && password != null){
			return true;
		}else {
			return false;
		}
	}



	/**
	 * 获取tutor的id
	 */
	public static long getTutorIdfromSP(Context ct) {
		SharedPreferences setting = ct.getSharedPreferences("myInfo", ct.MODE_PRIVATE);
		return setting.getLong(Constants.TUTORID, 0);

	}

	/**
	 * 删除指定的数据
	 * @param key
	 * @return
	 */
	public static boolean removeConfig(String key){
		SharedPreferences setting = MyApplication.getInstances().getSharedPreferences("myInfo", MyApplication.getInstances().MODE_PRIVATE);
		Editor editor = setting.edit();
		editor.remove(key);
		return editor.commit();
	}
}
