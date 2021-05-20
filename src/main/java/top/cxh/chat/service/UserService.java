package top.cxh.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.cxh.chat.bean.User;
import top.cxh.chat.bean.UserExample;
import top.cxh.chat.bean.UserExample.Criteria;
import top.cxh.chat.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;
	
	/**
	 * 添加用户账号
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		return userMapper.insert(user) != 0;
	}
	
	/**
	 * 检查账号是否存在
	 * @param account
	 * @return
	 */
	public boolean checkAccount(String account) {
		return userMapper.selectByPrimaryKey(account) != null;
	}
	
	/**
	 * 检查密码是否正确
	 * @param account
	 * @param password
	 * @return
	 */
	public boolean checkPassword(String account,String password) {
		UserExample ex = new UserExample();
		Criteria cr = ex.createCriteria();
		cr.andAccountEqualTo(account);
		cr.andPasswordEqualTo(password);
		return userMapper.selectByExample(ex).size() != 0;
	}
	
}
