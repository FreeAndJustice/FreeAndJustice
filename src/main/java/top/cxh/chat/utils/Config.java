package top.cxh.chat.utils;

import io.netty.channel.Channel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Config {

	//文件存储路径
	public static String storagePath = "F:\\images\\BlueSeaChat";
	
	//存储在线用户
	public static Map<String,Channel> onlineUsers = new HashMap<String,Channel>();
	
	/**
	 * 生成群聊账号
	 * @return
	 */
	public static String createGroupId() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(d);
	}
	
}
