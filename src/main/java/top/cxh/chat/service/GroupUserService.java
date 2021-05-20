package top.cxh.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.GroupUser;
import top.cxh.chat.bean.GroupUserExample;
import top.cxh.chat.bean.GroupUserExample.Criteria;
import top.cxh.chat.mapper.GroupUserMapper;

@Service
public class GroupUserService {

	@Autowired
	GroupUserMapper groupUserMapper;
	
	/**
	 * 添加群成员
	 * @param groupUser
	 * @return
	 */
	public boolean addGroupUser(GroupUser groupUser) {
		return groupUserMapper.insertSelective(groupUser) != 0;
	}
	
	/**
	 * 修改群成员角色
	 * @param groupUser
	 * @return
	 */
	public boolean updateGroupUser(GroupUser groupUser) {
		return groupUserMapper.updateByPrimaryKeySelective(groupUser) != 0;
	}
	
	/**
	 * 用户是否在群里
	 * @param groupId
	 * @param account
	 * @return
	 */
	public boolean isExistGroup(String groupId,String account) {
		GroupUserExample ex = new GroupUserExample();
		Criteria cr = ex.createCriteria();
		cr.andGroupIdEqualTo(groupId);
		cr.andAccountEqualTo(account);
		return groupUserMapper.selectByExample(ex).size() != 0;
	}
	
	/**
	 * 移除群成员
	 * @param id
	 * @return
	 */
	public boolean delGroupUser(Integer id) {
		return groupUserMapper.deleteByPrimaryKey(id) != 0;
	}
	
	/**
	 * 移除全部成员
	 * @param groupId
	 * @return
	 */
	public boolean delGroupUsers(String groupId) {
		GroupUserExample ex = new GroupUserExample();
		Criteria cr = ex.createCriteria();
		cr.andGroupIdEqualTo(groupId);
		return groupUserMapper.deleteByExample(ex) != 0;
	}
	
	/**
	 * 用户退出群聊
	 * @param account
	 * @param groupId
	 * @return
	 */
	public boolean outGroup(String account,String groupId) {
		GroupUserExample ex = new GroupUserExample();
		Criteria cr = ex.createCriteria();
		cr.andAccountEqualTo(account);
		cr.andGroupIdEqualTo(groupId);
		return groupUserMapper.deleteByExample(ex) != 0;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean delGroupUsers(List<Integer> ids) {
		GroupUserExample ex = new GroupUserExample();
		Criteria cr = ex.createCriteria();
		cr.andIdIn(ids);
		return groupUserMapper.deleteByExample(ex) != 0;
	}
	
	/**
	 * 获取我所在的群
	 * @param account
	 * @return
	 */
	public List<GroupUser> getMyGroups(String account){
		return groupUserMapper.selectMyGroups(account);
	}
	
	/**
	 * 获取群成员
	 * @param groupId
	 * @return
	 */
	public List<GroupUser> getGroupUsers(String groupId){
		return groupUserMapper.selectGroupUserInfo(groupId);
	}
	
}
