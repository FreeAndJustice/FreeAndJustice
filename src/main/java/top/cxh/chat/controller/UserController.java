package top.cxh.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.cxh.chat.bean.Friends;
import top.cxh.chat.bean.Msg;
import top.cxh.chat.bean.User;
import top.cxh.chat.bean.UserInfo;
import top.cxh.chat.service.FriendsService;
import top.cxh.chat.service.UserInfoService;
import top.cxh.chat.service.UserService;
import top.cxh.chat.utils.Beans;
import top.cxh.chat.utils.Config;
import top.cxh.chat.utils.MapUtils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserInfoService userInfoService;
	
	/**
	 * 登录
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	Msg login(@RequestParam("account") String account,
			@RequestParam("password") String password) {
		Msg msg = new Msg();
		System.out.println(account);
		boolean flag = userService.checkAccount(account);
		if(flag) {
			flag = userService.checkPassword(account, password);
			if(flag) {
				msg.setCode(100);
				msg.setMsg("登录成功");
				UserInfo ui = userInfoService.getUserInfoByAccount(account);
				//查询为空时要将用户信息写进数据库
				if(ui == null) {
					ui = new UserInfo();
					ui.setAccount(account);
					ui.setUserName(account);
					ui.setCreateDate(new Date());
					userInfoService.addUserInfo(ui);
				}
				msg.add("userInfo", ui);
			}else {
				msg.setCode(102);
				msg.setMsg("密码错误");
			}
		}else {
			msg.setCode(101);
			msg.setMsg("账号不存在");
		}
		return msg;
	}
	
	/**
	 * 修改个人信息
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	@ResponseBody
	Msg updateImage(UserInfo userInfo) {
		if(userInfo.getProvince() != null && !userInfo.getProvince().equals("")) {
			Map<String, Double> map = MapUtils.getLngAndLat(userInfo.getProvince() + userInfo.getCity() + userInfo.getCounty() + userInfo.getLocation());
			userInfo.setLng(map.get("lng"));
			userInfo.setLat(map.get("lat"));
		}
		boolean flag = userInfoService.updateUserInfoByAccount(userInfo);
		Msg msg = new Msg();
		if(flag) {
			if(userInfo.getUserImage() != null && !userInfo.getUserImage().equals("")) {
				File file = new File(Config.storagePath + "\\userImage");
				File[] files = file.listFiles();
				for(File f : files) {
					if(!f.getName().equals(userInfo.getUserImage())) {
						if(f.getName().contains("-")) {
							if(f.getName().split("-")[0].equals(userInfo.getUserImage())) {
								f.delete();
								break;
							}
						}
					}
				}
			}
			msg.setCode(100);
			msg.setMsg("修改成功");
		}else {
			msg.setCode(200);
			msg.setMsg("修改失败");
		}
		return msg;
	}
	
	/**
	 * 注册
	 * @param account
	 * @param password
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	@ResponseBody
	Msg register(@RequestParam("account") String account,
				@RequestParam("password") String password,
				@RequestParam("userName") String userName) {
		Msg msg = new Msg();
		boolean flag = userService.checkAccount(account);
		if(flag) {
			msg.setCode(200);
			msg.setMsg("账号已存在");
		}else {
			User u = new User();
			u.setAccount(account);
			u.setPassword(password);
			flag = userService.addUser(u);
			if(flag){
				msg.setCode(100);
				msg.setMsg("注册成功");
				if(userName.equals("")) {
					userName = account;
				}
				UserInfo ui = new UserInfo();
				ui.setAccount(account);
				ui.setUserName(userName);
				userInfoService.addUserInfo(ui);
				Friends f = new Friends();
				f.setMyAccount(account);
				f.setFriendAccount(account);
				f.setCreateDate(new Date());
				Beans.getBean("friendsService", FriendsService.class).addFriends(f);
			}else {
				msg.setCode(300);
				msg.setMsg("注册出错，请重试");
			}
		}
		return msg;
	}
	
	/**
	 * 获取账号信息
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getUserInfo",method=RequestMethod.GET)
	@ResponseBody
	Msg getUserInfo(@RequestParam("account") String account) {
		Msg msg = new Msg();
		UserInfo ui = userInfoService.getUserInfoByAccount(account);
		if(ui == null) {
			msg.setCode(200);
			msg.setMsg("没有该用户");
		}else {
			msg.setCode(100);
			msg.setMsg("查询成功");
			msg.add("data", ui);
		}
		return msg;
	}
	/**
	 * 搜索好友
	 * @param keys
	 * @return
	 */
	@RequestMapping(value="/searchUsers",method=RequestMethod.GET)
	@ResponseBody
	Msg searchUsers(@RequestParam("keys") String keys) {
		List<UserInfo> uis = userInfoService.searchUsers(keys);
		return Msg.success().add("data", uis);
	}
	
}
