package top.cxh.chat.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.cxh.chat.bean.Circle;
import top.cxh.chat.bean.CircleImage;
import top.cxh.chat.bean.Msg;
import top.cxh.chat.service.CircleImageService;
import top.cxh.chat.service.CircleService;

@Controller
@RequestMapping("/circle")
public class CircleController {

	@Autowired
	CircleService circleService;
	
	@Autowired
	CircleImageService circleImageService;
	
	/**
	 * 添加动态
	 * @param account
	 * @param context
	 * @return
	 */
	@RequestMapping(value="/addCircle",method=RequestMethod.POST)
	@ResponseBody
	Msg addCircle(@RequestParam("account") String account,
				@RequestParam("context") String context) {
		String circleId = account + new Date().getTime();
		if(context == null || context.equals("")) {
			context = null;
		}
		Circle circle = new Circle(circleId,context,new Date(),account);
		boolean flag = circleService.addCircle(circle);
		Msg msg = new Msg();
		if(flag) {
			msg.setCode(100);
			msg.setMsg("添加成功");
			msg.add("circleId", circleId);
		}else {
			msg.setCode(200);
			msg.setMsg("添加失败");
		}
		return msg;
	}
	
	/**
	 * 动态图片添加
	 * @param circleId
	 * @param imageNames
	 * @return
	 */
	@RequestMapping(value="/addCircleImages",method=RequestMethod.POST)
	@ResponseBody
	Msg addCircleImages(@RequestParam("circleId") String circleId,
					@RequestParam("imageNames") String imageNames) {
		Msg msg = new Msg();
		List<CircleImage> cis = new ArrayList<CircleImage>();
		if(!imageNames.contains("@")) {
			CircleImage ci = new CircleImage();
			ci.setCircleId(circleId);
			ci.setImageName(imageNames);
			cis.add(ci);
		}else {
			String[] names = imageNames.split("@");
			for(String name : names) {
				CircleImage ci = new CircleImage();
				ci.setCircleId(circleId);
				ci.setImageName(name);
				cis.add(ci);
			}
		}
		boolean flag = circleImageService.addCircleImages(cis);
		if(flag) {
			msg.setCode(100);
			msg.setMsg("发布成功");
		}else {
			msg.setCode(100);
			msg.setMsg("发布失败");
		}
		return msg;
	}
	
	@RequestMapping(value="/getFriendCircles/{account}",method=RequestMethod.GET)
	@ResponseBody
	Msg getFriendCircles(@PathVariable("account") String account,
					@RequestParam(value = "pn", defaultValue = "1") Integer pn,
					@RequestParam("type") Integer type,
					@RequestParam("date") String date) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(pn < 1) {
			pn = 1;
		}
		System.out.println(date);
		map.put("account", account);
		map.put("index", (pn - 1) * 10);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		try {
			if(type == 0) {//下拉加载
				map.put("nextDate", df.parse(date));
			}else if(type == 1) {//上拉加载
				map.put("lastDate", df.parse(date));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Circle> myCircles = circleService.getMyFriendCircles(map);
		return Msg.success().add("data", myCircles);
	}
	
}
