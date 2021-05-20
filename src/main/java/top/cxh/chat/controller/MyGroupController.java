package top.cxh.chat.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import top.cxh.chat.bean.Msg;
import top.cxh.chat.bean.MyGroup;
import top.cxh.chat.service.MyGroupService;

@Controller
@RequestMapping("/group")
public class MyGroupController {

	@Autowired
	MyGroupService myGroupService;
	
	/**
	 * 新建分组
	 * @param groupName
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/addGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg addGroup(@RequestParam("groupName") String groupName,
			@RequestParam("account") String account) {
		Msg msg = new Msg();
		boolean flag = myGroupService.isExistsGroup(groupName, account);
		if(flag) {
			msg.setCode(101);
			msg.setMsg("分组名已存在");
		}else {
			MyGroup mg = new MyGroup();
			mg.setGroupName(groupName);
			mg.setAccount(account);
			flag = myGroupService.addGroup(mg);
			if(flag) {
				msg.setCode(100);
				msg.setMsg("分组创建成功");
			}else {
				msg.setCode(102);
				msg.setMsg("分组创建失败");
			}
		}
		return msg;
	}
	
	/**
	 * 删除分组
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delGroup/{id}",method=RequestMethod.POST)
	@ResponseBody
	Msg delGroup(@PathParam("id") Integer id) {
		boolean flag = myGroupService.delGroup(id);
		if(flag) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	/**
	 * 修改分组
	 * @param groupName
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/updateGroup",method=RequestMethod.POST)
	@ResponseBody
	Msg updateGroup(@RequestParam("groupName") String groupName,
			@RequestParam("account") String account) {
		Msg msg = new Msg();
		boolean flag = myGroupService.isExistsGroup(groupName, account);
		if(flag) {
			msg.setCode(101);
			msg.setMsg("分组名已存在");
		}else {
			MyGroup mg = new MyGroup();
			mg.setGroupName(groupName);
			mg.setAccount(account);
			flag = myGroupService.updateGroup(mg);
			if(flag) {
				msg.setCode(100);
				msg.setMsg("分组修改成功");
			}else {
				msg.setCode(102);
				msg.setMsg("分组修改失败");
			}
		}
		return msg;
	}
	
	/**
	 * 获取分组
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/getGroups/{account}",method=RequestMethod.GET)
	@ResponseBody
	Msg getGroups(@PathVariable("account") String account) {
		return Msg.success().add("data", myGroupService.getMyGroups(account));
	}
}
