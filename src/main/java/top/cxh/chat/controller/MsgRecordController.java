package top.cxh.chat.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import top.cxh.chat.bean.GroupChat;
import top.cxh.chat.bean.GroupUser;
import top.cxh.chat.bean.Msg;
import top.cxh.chat.bean.MsgRecord;
import top.cxh.chat.bean.UserInfo;
import top.cxh.chat.service.FriendsService;
import top.cxh.chat.service.GroupChatService;
import top.cxh.chat.service.GroupUserService;
import top.cxh.chat.service.MsgRecordService;
import top.cxh.chat.service.UserInfoService;
import top.cxh.chat.utils.Beans;
import top.cxh.chat.utils.Config;

@Controller
@RequestMapping("/msgRecord")
public class MsgRecordController {

	@Autowired
	MsgRecordService msgRecordService;
	
	/**
	 * 申请消息处理
	 * @param fromAccount
	 * @param toAccount
	 * @return
	 */
	@RequestMapping(value="/addMsgRecord",method=RequestMethod.POST)
	@ResponseBody
	Msg addMsgRecord(@RequestParam("fromAccount") String fromAccount,
						@RequestParam("toAccount") String toAccount) {
		Msg msg = new Msg();
		boolean flag = Beans.getBean("friendsService", FriendsService.class).isFriends(fromAccount, toAccount);
		if(flag) {
			msg.setCode(300);
			msg.setMsg("你们已经是好友了");
		}else {
			flag = msgRecordService.isExistReqMsg(fromAccount, toAccount);
			if(flag) {
				msg.setCode(200);
				msg.setMsg("已经提交过申请，等待对方审核");
			}else {
				msg.setCode(100);
				msg.setMsg("申请已发送");
				//写入数据库
				Date d = new Date();
				MsgRecord mr = new MsgRecord("申请消息",fromAccount,toAccount,"req","self",d);
				msgRecordService.addMsgRecord(mr);
				
				//用户在线直接发送过去
				if(Config.onlineUsers.containsKey(toAccount)) {
					Channel c = Config.onlineUsers.get(toAccount);
					if(c.isActive()) {//通道保持连通
						JSONObject json = new JSONObject();
						json.put("msgCode", 102);
						json.put("createDate", d);
						UserInfo ui = Beans.getBean("userInfoService", UserInfoService.class).getUserInfoByAccount(fromAccount);
						json.put("userInfo", ui);
						TextWebSocketFrame tsf = new TextWebSocketFrame(json.toJSONString());
						c.writeAndFlush(tsf);
					}
				}
			}
		}
		return msg;
	}
	
	/**
	 * 申请加群消息处理
	 * @param fromAccount
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/reqAddGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg reqAddGroup(@RequestParam("fromAccount") String fromAccount,
						@RequestParam("groupId") String groupId) {
		Msg msg = new Msg();
		GroupUserService groupUserService = Beans.getBean("groupUserService", GroupUserService.class);
		boolean flag = groupUserService.isExistGroup(groupId, fromAccount);
		if(flag) {
			msg.setCode(300);
			msg.setMsg("你已在该群聊");
		}else {
			flag = msgRecordService.isExistReqMsg(fromAccount, groupId);
			if(flag) {
				msg.setCode(200);
				msg.setMsg("已经提交过申请，等待群主或管理员审核");
			}else {
				msg.setCode(100);
				msg.setMsg("申请已发送");
				//写入数据库
				Date d = new Date();
				MsgRecord mr = new MsgRecord("申请消息",fromAccount,groupId,"req","group",d);
				msgRecordService.addMsgRecord(mr);
				
				List<GroupUser> gus = groupUserService.getGroupUsers(groupId);
				for(GroupUser gu : gus) {
					if(gu.getGroupRole() == 0 || gu.getGroupRole() == 1) {
						//用户在线直接发送过去
						if(Config.onlineUsers.containsKey(gu.getAccount())) {
							Channel c = Config.onlineUsers.get(gu.getAccount());
							if(c.isActive()) {//通道保持连通
								JSONObject json = new JSONObject();
								json.put("msgCode", 102);
								json.put("createDate", d);
								GroupChat gc = Beans.getBean("groupChatService", GroupChatService.class).getGroupChatByGroupId(groupId);
								json.put("groupInfo", gc);
								TextWebSocketFrame tsf = new TextWebSocketFrame(json.toJSONString());
								c.writeAndFlush(tsf);
							}
						}
					}
				}
				
			}
		}
		return msg;
	}
	/**
	 * 获取给我的验证消息
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getMyReqMsg",method=RequestMethod.GET)
	@ResponseBody
	Msg getMyReqMsg(@RequestParam("account") String account) {
		List<MsgRecord> mrs = msgRecordService.getReqMsgRecord(account);
		return Msg.success().add("data", mrs);
	}
	/**
	 * 获取加群的验证消息
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getMyGroupReqMsg",method=RequestMethod.GET)
	@ResponseBody
	Msg getMyGroupReqMsg(@RequestParam("account") String account) {
		List<GroupUser> gus = Beans.getBean("groupUserService", GroupUserService.class).getMyGroups(account);
		List<MsgRecord> msgRecords = new ArrayList<MsgRecord>();
		for(GroupUser gu : gus) {
			if(gu.getGroupRole() == 0 || gu.getGroupRole() == 1) {
				List<MsgRecord> mrs = msgRecordService.getGroupReqMsgRecord(gu.getGroupId());
				msgRecords.addAll(mrs);
			}
		}
		Collections.sort(msgRecords, new DateSort());
		return Msg.success().add("data", msgRecords);
	}
	/**
	 * 拒绝申请
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateMyReqMsgState",method=RequestMethod.POST)
	@ResponseBody
	Msg updateMyReqMsgState(@RequestParam("id") Integer id) {
		MsgRecord mr = new MsgRecord();
		mr.setId(id);
		mr.setMsgState(3);
		boolean flag = msgRecordService.updateMsgState(mr);
		if(flag) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	/**
	 * 离线消息发送
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/offlineMsg",method=RequestMethod.GET)
	@ResponseBody
	Msg offlineMsg(@RequestParam("account") String account) {
		Msg msg = new Msg();
		List<MsgRecord> mrs = msgRecordService.getOfflineMsg(account);
		for(MsgRecord mr : mrs) {
			if(Config.onlineUsers.containsKey(account)) {
				Channel c = Config.onlineUsers.get(account);
				if(c.isActive()) {
					if(mr.getSendType().equals("self")) {
						JSONObject param = new JSONObject();
						param.put("msgCode", 101);
						param.put("fromAccount", mr.getFromAccount());
						param.put("toAccount", account);
						param.put("msgType", "text");
						param.put("context", mr.getContext());
						param.put("createDate", mr.getCreateDate());
						TextWebSocketFrame tsf = new TextWebSocketFrame(param.toJSONString());
						c.writeAndFlush(tsf);
						MsgRecord m = new MsgRecord();
						m.setId(mr.getId());
						m.setMsgState(1);
						msgRecordService.updateMsgState(m);
					}else {
						JSONObject param = new JSONObject();
						param.put("msgCode", 104);
						param.put("fromAccount", mr.getFromAccount());
						param.put("toAccount", mr.getContext().split("#@#")[1]);
						param.put("msgType", "text");
						param.put("context", mr.getContext().split("#@#")[0]);
						param.put("createDate", mr.getCreateDate());
						UserInfo ui = Beans.getBean("userInfoService", UserInfoService.class).getUserInfoByAccount(mr.getFromAccount());
						param.put("user", ui);
						List<GroupUser> groupUsers = Beans.getBean("groupUserService", GroupUserService.class).getGroupUsers(param.getString("toAccount"));
						for(GroupUser gu : groupUsers) {
							if(gu.getAccount().equals(account)) {
								param.put("role", gu.getGroupRole());
								TextWebSocketFrame tsf = new TextWebSocketFrame(param.toJSONString());
								c.writeAndFlush(tsf);
								MsgRecord m = new MsgRecord();
								m.setId(mr.getId());
								m.setMsgState(1);
								msgRecordService.updateMsgState(m);
								break;
							}
						}
					}
				}
			}
		}
		return msg;
	}
}
class DateSort implements Comparator<MsgRecord>{
	@Override
	public int compare(MsgRecord o1, MsgRecord o2) {
		return o2.getCreateDate().compareTo(o1.getCreateDate());
	}
}