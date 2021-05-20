package top.cxh.chat.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class MapUtils {
	public static void main(String[] args) {
		String str = "江苏省南京市雨花台区江苏智库智能科技有限公司";
		System.out.println(getLngAndLat(str));;
		
	}
	/**
	 * 基于百度的地址解析
	 * @param address
	 * @return
	 */
	public static Map<String, Double> getLngAndLat(String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		String url = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=fSPzNDfQ7UIDikGjoguK4XG3TGGGm4Yj";
		JSONObject json = JSONObject.parseObject(loadJSON(url));
		if(json.getInteger("status") == 0) {
			JSONObject result = json.getJSONObject("result");
			JSONObject location = result.getJSONObject("location");
			map.put("lng", location.getDouble("lng"));
			map.put("lat", location.getDouble("lat"));
		}else {
			return null;
		}
		return map;
	}
	/**
	 * IP地址解析
	 * @param IP
	 * @return
	 */
	public static String getAddressByIP(String IP){ 
		if(IP.equals("127.0.0.1") || IP.equals("localhost")) {
			return "本机";
		}
		String url = "https://api.map.baidu.com/location/ip?ip="+IP+"&ak=mRdryUGzZpUi1IHw2vyaaqtzYv29hIux&coor=bd09ll";
		try {
			String json = loadJSON(url);
			JSONObject obj = JSONObject.parseObject(json);
			JSONObject obj1 = (JSONObject) obj.get("content");
			return obj1.get("address").toString();
		}catch(Exception e) {
			return "解析出错";
		}
	}
	
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
