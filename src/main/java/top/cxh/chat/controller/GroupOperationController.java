package top.cxh.chat.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.cxh.chat.bean.GroupChat;
import top.cxh.chat.bean.GroupUser;
import top.cxh.chat.bean.Msg;
import top.cxh.chat.bean.MsgRecord;
import top.cxh.chat.service.GroupChatService;
import top.cxh.chat.service.GroupUserService;
import top.cxh.chat.service.MsgRecordService;
import top.cxh.chat.utils.Beans;
import top.cxh.chat.utils.Config;

@Controller
@RequestMapping("/group")
public class GroupOperationController {

	@Autowired
	GroupChatService groupChatService;
	
	@Autowired
	GroupUserService groupUserService;
	
	/**
	 * 
	 * @param account
	 * @param groupName
	 * @return
	 */
	@RequestMapping(value="/createGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg createGroup(@RequestParam("account") String account,
					@RequestParam("groupName") String groupName,
					@RequestParam("users") String users) {
		Msg msg = new Msg();
		String groupId = Config.createGroupId();
		GroupChat gc = new GroupChat(groupId,groupName,new Date());
		boolean flag = groupChatService.addGroupChat(gc);
		if(flag) {
			msg.setCode(100);
			msg.setMsg("创建成功");
			GroupUser gu = new GroupUser(account,0,groupId);
			groupUserService.addGroupUser(gu);
			if(users != null  && !users.equals("")) {
				if(users.contains(",")) {
					String[] as = users.split(",");
					for(String a : as) {
						gu.setAccount(a);
						gu.setGroupRole(2);
						groupUserService.addGroupUser(gu);
					}
				}else {
					gu.setAccount(users);
					gu.setGroupRole(2);
					groupUserService.addGroupUser(gu);
				}
			}
		}else {
			msg.setCode(200);
			msg.setMsg("创建群聊出错");
		}
		return msg;
	}
	
	/**
	 * 获取我所在的群聊
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getMyGroups",method=RequestMethod.GET)
	@ResponseBody
	Msg getMyGroups(@RequestParam("account") String account) {
		List<GroupUser> gus = groupUserService.getMyGroups(account);
		return Msg.success().add("data", gus);
	}
	
	/**
	 * 群成员
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/getGroupUsers",method=RequestMethod.GET)
	@ResponseBody
	Msg getGroupUsers(@RequestParam("groupId") String groupId) {
		List<GroupUser> gus = groupUserService.getGroupUsers(groupId);
		return Msg.success().add("data", gus);
	}
	
	/**
	 * 获取群资料
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/getGroupData",method=RequestMethod.GET)
	@ResponseBody
	Msg getGroupData(@RequestParam("groupId") String groupId) {
		GroupChat gc = groupChatService.getGroupChatByGroupId(groupId);
		return Msg.success().add("data", gc);
	}
	
	/**
	 * 修改群头像
	 * @param groupId
	 * @param groupImage
	 * @return
	 */
	@RequestMapping(value="/updateGroupImage",method=RequestMethod.POST)
	@ResponseBody
	Msg updateGroupImage(@RequestParam("groupId") String groupId,
						@RequestParam("groupImage") String groupImage) {
		System.out.println(groupId + ":" + groupImage);
		GroupChat gc = new GroupChat();
		gc.setGroupId(groupId);
		gc.setGroupImage(groupImage);
		boolean flag = groupChatService.updateGroupChat(gc);
		Msg msg = new Msg();
		if(flag) {
			msg.setCode(100);
			msg.setMsg("修改成功");
			File file = new File(Config.storagePath + "\\userImage");
			if(file.isDirectory()) {
				File[] files = file.listFiles();
				for(File f : files) {
					if(!f.getName().equals(groupImage)) {
						if(f.getName().contains("-")) {
							if(f.getName().split("-")[0].equals(groupId)) {
								if(f.isFile()) {
									f.delete();
								}
							}
						}
					}
				}
			}
		}else {
			msg.setCode(200);
			msg.setMsg("修改失败");
		}
		return msg;
	}
	
	/**
	 * 修改群资料
	 * @param groupId
	 * @param groupName
	 * @param groupImage
	 * @return
	 */
	@RequestMapping(value="/updateGroupData",method=RequestMethod.POST)
	@ResponseBody
	Msg updateGroupData(@RequestParam("groupId") String groupId,
						@RequestParam("groupName") String groupName,
						@RequestParam("groupImage") String groupImage) {
		GroupChat gc = new GroupChat();
		gc.setGroupId(groupId);
		if(!groupName.equals("")) {
			gc.setGroupName(groupName);
		}
		if(!groupImage.equals("")) {
			gc.setGroupImage(groupImage);
		}
		boolean flag = groupChatService.updateGroupChat(gc);
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
	 * 移除群成员
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delGroupUser",method=RequestMethod.POST)
	@ResponseBody
	Msg delGroupUser(@RequestParam("id") Integer id) {
		Msg msg = new Msg();
		boolean flag = groupUserService.delGroupUser(id);
		if(flag) {
			msg.setCode(100);
			msg.setMsg("移除成功");
		}else {
			msg.setCode(200);
			msg.setMsg("移除失败");
		}
		return msg;
	}
	
	/**
	 * 同意用户进群
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/agreeUserForGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg agreeUserForGroup(@RequestParam("id") Integer id,
						@RequestParam("groupId") String groupId,
						@RequestParam("account") String account) {
		Msg msg = new Msg();
		GroupUser gu = new GroupUser();
		gu.setAccount(account);
		gu.setGroupId(groupId);
		gu.setGroupRole(2);
		System.out.println(gu);
		boolean flag = groupUserService.addGroupUser(gu);
		if(flag) {
			MsgRecord mr = new MsgRecord();
			mr.setId(id);
			mr.setMsgState(2);
			Beans.getBean("msgRecordService", MsgRecordService.class).updateMsgState(mr);
			msg.setCode(100);
			msg.setMsg("已同意");
		}else {
			msg.setCode(200);
			msg.setMsg("操作失败");
		}
		return msg;
	}
	
	/**
	 * 添加好友进群
	 * @param groupId
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/addGroupUser",method=RequestMethod.POST)
	@ResponseBody
	Msg addGroupUser(@RequestParam("groupId") String groupId,
					@RequestParam("users") String users) {
		Msg msg = new Msg();
		boolean flag = false;
		if(users.contains(",")) {
			String[] as = users.split(",");
			GroupUser gu = new GroupUser();
			gu.setGroupId(groupId);
			for(String a : as) {
				gu.setAccount(a);
				gu.setGroupRole(2);
				flag = groupUserService.addGroupUser(gu);
			}
		}else {
			GroupUser gu = new GroupUser();
			gu.setGroupId(groupId);
			gu.setAccount(users);
			gu.setGroupRole(2);
			flag = groupUserService.addGroupUser(gu);
		}
		if(flag) {
			msg.setCode(100);
			msg.setMsg("添加成功");
		}else {
			msg.setCode(200);
			msg.setMsg("添加失败");
		}
		return msg;
	}
	
	/**
	 * 用户是否在群里
	 * @param groupId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/userIsExistGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg userIsExistGroup(@RequestParam("groupId") String groupId,
					@RequestParam("account") String account) {
		Msg msg = new Msg();
		boolean flag = groupUserService.isExistGroup(groupId, account);
		if(flag) {
			msg.setCode(200);
			msg.setMsg("用户已存在");
		}else {
			msg.setCode(100);
			msg.setMsg("用户不存在");
		}
		return msg;
	}
	/**
	 * 设置群成员角色
	 * @param groupId
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/updateGroupUser",method=RequestMethod.POST)
	@ResponseBody
	Msg updateGroupUser(@RequestParam("id") Integer id,
					@RequestParam("role") Integer role) {
		Msg msg = new Msg();
		GroupUser gu = new GroupUser();
		gu.setId(id);
		gu.setGroupRole(role);
		boolean flag = groupUserService.updateGroupUser(gu);
		if(flag) {
			msg.setCode(100);
			msg.setMsg("设置成功");
		}else {
			msg.setCode(200);
			msg.setMsg("设置失败");
		}
		return msg;
	}
	
	/**
	 * 退出群聊
	 * @param groupId
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/outGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg outGroup(@RequestParam("groupId") String groupId,
					@RequestParam("account") String account) {
		Msg msg = new Msg();
		boolean flag = groupUserService.outGroup(account, groupId);
		if(flag) {
			msg.setCode(100);
			msg.setMsg("退出成功");
		}else {
			msg.setCode(200);
			msg.setMsg("退出失败");
		}
		return msg;
	}
	
	/**
	 * 解散群聊
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/cancelGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg cancelGroup(@RequestParam("groupId") String groupId) {
		Msg msg = new Msg();
		
		boolean flag = groupChatService.delGroupChat(groupId);
		if(flag) {
			flag = groupUserService.delGroupUsers(groupId);
		}
		if(flag) {
			msg.setCode(100);
			msg.setMsg("解散成功");
		}else {
			msg.setCode(200);
			msg.setMsg("解散失败");
		}
		return msg;
	}
	
	/**
	 * 搜索群聊
	 * @param keys
	 * @return
	 */
	@RequestMapping(value="/searchGroup",method=RequestMethod.GET)
	@ResponseBody
	Msg searchGroup(@RequestParam("keys") String keys) {
		List<GroupChat> gcs = groupChatService.searchGroup(keys);
		return Msg.success().add("data", gcs);
	}
}
