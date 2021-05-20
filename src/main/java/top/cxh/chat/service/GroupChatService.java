package top.cxh.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.GroupChat;
import top.cxh.chat.mapper.GroupChatMapper;

@Service
public class GroupChatService {

	@Autowired
	GroupChatMapper groupChatMapper;
	
	/**
	 * 创建群聊
	 * @param groupChat
	 * @return
	 */
	public boolean addGroupChat(GroupChat groupChat) {
		return groupChatMapper.insertSelective(groupChat) != 0;
	}
	
	/**
	 * 修改群信息
	 * @param groupChat
	 * @return
	 */
	public boolean updateGroupChat(GroupChat groupChat) {
		return groupChatMapper.updateByPrimaryKeySelective(groupChat) != 0;
	}
	
	/**
	 * 删除群聊
	 * @param groupId
	 * @return
	 */
	public boolean delGroupChat(String groupId) {
		return groupChatMapper.deleteByPrimaryKey(groupId) != 0;
	}
	
	/**
	 * 获取群资料
	 * @param groupId
	 * @return
	 */
	public GroupChat getGroupChatByGroupId(String groupId) {
		return groupChatMapper.selectByPrimaryKey(groupId);
	}
	
	/**
	 * 搜索群聊
	 * @param keys
	 * @return
	 */
	public List<GroupChat> searchGroup(String keys){
		return groupChatMapper.selectBySearchKeys(keys);
	}
	
}
