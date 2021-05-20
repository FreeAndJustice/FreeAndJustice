package top.cxh.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.Friends;
import top.cxh.chat.bean.FriendsExample;
import top.cxh.chat.bean.FriendsExample.Criteria;
import top.cxh.chat.mapper.FriendsMapper;

@Service
public class FriendsService {

	@Autowired
	FriendsMapper friendsMapper;
	
	/** 
	 * 添加好友
	 * @param friends
	 * @return
	 */
	public boolean addFriends(Friends friends) {
		return friendsMapper.insertSelective(friends) != 0;
	}
	
	/**
	 * 是否已经是好友
	 * @param myAccount
	 * @param friendAccount
	 * @return
	 */
	public boolean isFriends(String myAccount,String friendAccount) {
		FriendsExample ex = new FriendsExample();
		Criteria cr = ex.createCriteria();
		cr.andMyAccountEqualTo(myAccount);
		cr.andFriendAccountEqualTo(friendAccount);
		return friendsMapper.selectByExample(ex).size() > 0;
	}
	/**
	 * 修改好友备注
	 * @param friends
	 * @return
	 */
	public boolean updateFriendName(Friends friends) {
		return friendsMapper.updateByPrimaryKeySelective(friends) != 0;
	}
	
	/**
	 * 修改分组或备注
	 * @param map
	 * @return
	 */
	public boolean updateMyGroup(Map<String,Object> map) {
		return friendsMapper.updateByMyExample(map) != 0;
	}
	
	/**
	 * 查询我的好友
	 * @param account
	 * @return
	 */
	public List<Friends> getMyFriends(String account){
		return friendsMapper.selectMyFriendsByAccount(account);
	}
	
	/**
	 * 从我的好友中搜索
	 * @param map
	 * @return
	 */
	public List<Friends> getSearchMyFriends(Map<String,Object> map){
		return friendsMapper.selectMyFriendsByKeys(map);
	}
	
	/**
	 * 获取好友所在组信息
	 * @param myAccount
	 * @param friendAccount
	 * @return
	 */
	public Friends getFriendGroup(String myAccount,String friendAccount) {
		FriendsExample ex = new FriendsExample();
		Criteria cr = ex.createCriteria();
		cr.andMyAccountEqualTo(myAccount);
		cr.andFriendAccountEqualTo(friendAccount);
		List<Friends> selectByExample = friendsMapper.selectByExample(ex);
		if(selectByExample.size() > 0) {
			return selectByExample.get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * 获取朋友圈动态
	 * @param account
	 * @return
	 */
	public List<Friends> getMyCircles(String account){
		return friendsMapper.selectMyCirclesByAccount(account);
	}
	
}
