package top.cxh.chat.controller;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.cxh.chat.bean.Friends;
import top.cxh.chat.bean.Msg;
import top.cxh.chat.bean.MsgRecord;
import top.cxh.chat.service.FriendsService;
import top.cxh.chat.service.MsgRecordService;
import top.cxh.chat.utils.Beans;

@Controller
@RequestMapping("/friends")
public class FriendsController {

	@Autowired
	FriendsService friendsService;
	
	/**
	 * 添加好友
	 * @param myAccount
	 * @param friendAccount
	 * @return
	 */
	@RequestMapping(value="/addFriend",method=RequestMethod.POST)
	@ResponseBody
	Msg addFriends(@RequestParam("myAccount") String myAccount,
					@RequestParam("friendAccount") String friendAccount,
					@RequestParam("id") Integer id) {
		Friends f = new Friends();
		f.setMyAccount(myAccount);
		f.setFriendAccount(friendAccount);
		f.setCreateDate(new Date());
		boolean flag = friendsService.addFriends(f);
		if(flag) {
			f.setMyAccount(friendAccount);
			f.setFriendAccount(myAccount);
			friendsService.addFriends(f);
			MsgRecord mr = new MsgRecord();
			mr.setId(id);
			mr.setMsgState(2);
			Beans.getBean("msgRecordService", MsgRecordService.class).updateMsgState(mr);
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	/**
	 * 修改分组或备注
	 * @param myAccount
	 * @param friendAccount
	 * @param friendName
	 * @param friendGroup
	 * @return
	 */
	@RequestMapping(value="/updateMyGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg updateMyGroup(@RequestParam("myAccount") String myAccount,
					@RequestParam("friendAccount") String friendAccount,
					@RequestParam("friendName") String friendName,
					@RequestParam("friendGroup") Integer friendGroup) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("myAccount", myAccount);
		map.put("friendAccount", friendAccount);
		map.put("friendName", friendName);
		map.put("friendGroup", friendGroup);
		boolean flag = friendsService.updateMyGroup(map);
		Msg msg = new Msg();
		if(flag) {
			msg.setCode(100);
			msg.setMsg("修改成功");
		}else {
			msg.setCode(200);
			msg.setMsg("修改失败");
		}
		return msg;
	}
	
	/**
	 * 获取用户所在组信息和备注
	 * @param myAccount
	 * @param friendAccount
	 * @return
	 */
	@RequestMapping(value="/getFriendGroupInfo",method=RequestMethod.GET)
	@ResponseBody
	Msg getFriendGroupInfo(@RequestParam("myAccount") String myAccount,
					@RequestParam("friendAccount") String friendAccount) {
		Friends f = friendsService.getFriendGroup(myAccount, friendAccount);
		Msg msg = new Msg();
		if(f == null) {
			msg.setCode(200);
			msg.setMsg("用户不能存在");
		}else {
			msg.setCode(100);
			msg.setMsg("查询成功");
			msg.add("data", f);
		}
		return msg;
	}
	
	/**
	 * 从好友中搜索
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/searchMyFriends",method=RequestMethod.GET)
	@ResponseBody
	Msg searchMyFriends(@RequestParam("myAccount") String myAccount,
					@RequestParam("keys") String keys) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("myAccount", myAccount);
		map.put("keys", keys);
		List<Friends> myFriends = friendsService.getSearchMyFriends(map);
		return Msg.success().add("data", myFriends);
	}
	
	
	@RequestMapping(value="/getFriend/{account}",method=RequestMethod.GET)
	@ResponseBody
	Msg getFriends(@PathVariable("account") String account) {
		List<Friends> myFriends = friendsService.getMyFriends(account);
		return Msg.success().add("data", myFriends);
	}
	
	/**
	 * 获取动态
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getCircles/{account}",method=RequestMethod.GET)
	@ResponseBody
	Msg getCircles(@PathVariable("account") String account,
					@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 10);//每页10条
		List<Friends> myCircles = friendsService.getMyCircles(account);
		PageInfo<Friends> page = new PageInfo<Friends>(myCircles, 5);//显示5页
		return Msg.success().add("data", page);
	}
}
