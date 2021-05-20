package top.cxh.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.UserInfo;
import top.cxh.chat.mapper.UserInfoMapper;

@Service
public class UserInfoService {

	@Autowired
	UserInfoMapper userInfoMapper;
	
	/**
	 * 添加用户信息
	 * @param userInfo
	 * @return
	 */
	public boolean addUserInfo(UserInfo userInfo) {
		return userInfoMapper.insertSelective(userInfo) != 0;
	}
	
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	public boolean updateUserInfoByAccount(UserInfo userInfo) {
		return userInfoMapper.updateByPrimaryKeySelective(userInfo) != 0;
	}
	
	/**
	 * 获取用户信息
	 * @param account
	 * @return
	 */
	public UserInfo getUserInfoByAccount(String account) {
		return userInfoMapper.selectByPrimaryKey(account);
	}
	
	/**
	 * 搜索好友
	 * @param keys
	 * @return
	 */
	public List<UserInfo> searchUsers(String keys){
		return userInfoMapper.selectByKeys(keys);
	}
	
}
