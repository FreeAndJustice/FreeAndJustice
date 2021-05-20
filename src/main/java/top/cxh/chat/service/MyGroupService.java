package top.cxh.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.MyGroup;
import top.cxh.chat.bean.MyGroupExample;
import top.cxh.chat.bean.MyGroupExample.Criteria;
import top.cxh.chat.mapper.MyGroupMapper;

@Service
public class MyGroupService {

	@Autowired
	MyGroupMapper myGroupMapper;
	
	/**
	 * 添加分组
	 * @param myGroup
	 * @return
	 */
	public boolean addGroup(MyGroup myGroup) {
		return myGroupMapper.insertSelective(myGroup) != 0;
	}
	
	/**
	 * 查询分组是否已存在
	 * @param groupName
	 * @param account
	 * @return
	 */
	public boolean isExistsGroup(String groupName,String account) {
		MyGroupExample ex = new MyGroupExample();
		Criteria cr = ex.createCriteria();
		cr.andAccountEqualTo(account);
		cr.andGroupNameEqualTo(groupName);
		return myGroupMapper.selectByExample(ex).size() > 0;
	}
	
	/**
	 * 删除分组
	 * @param id
	 * @return
	 */
	public boolean delGroup(int id) {
		return myGroupMapper.deleteByPrimaryKey(id) != 0;
	}
	
	/**
	 * 修改分组
	 * @param myGroup
	 * @return
	 */
	public boolean updateGroup(MyGroup myGroup) {
		return myGroupMapper.updateByPrimaryKeySelective(myGroup) != 0;
	}
	
	/**
	 * 获取我的分组
	 * @param account
	 * @return
	 */
	public List<MyGroup> getMyGroups(String account){
		MyGroupExample ex = new MyGroupExample();
		Criteria cr = ex.createCriteria();
		cr.andAccountEqualTo(account);
		return myGroupMapper.selectByExample(ex);
	}
	
}
