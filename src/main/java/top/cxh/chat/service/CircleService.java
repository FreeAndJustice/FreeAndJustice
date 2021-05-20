package top.cxh.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.Circle;
import top.cxh.chat.bean.CircleExample;
import top.cxh.chat.bean.CircleExample.Criteria;
import top.cxh.chat.mapper.CircleMapper;

@Service
public class CircleService {

	@Autowired
	CircleMapper circleMapper;
	
	/**
	 * 添加动态记录
	 * @param circle
	 * @return
	 */
	public boolean addCircle(Circle circle) {
		return circleMapper.insertSelective(circle) != 0;
	}
	
	/**
	 * 删除动态
	 * @param circleId
	 * @return
	 */
	public boolean delCircle(String circleId) {
		return circleMapper.deleteByPrimaryKey(circleId) != 0;
	}
	
	/**
	 * 获取自己的动态
	 * @param account
	 * @return
	 */
	public List<Circle> getMyCircle(String account){
		CircleExample ex = new CircleExample();
		Criteria cr = ex.createCriteria();
		cr.andAccountEqualTo(account);
		return circleMapper.selectByExample(ex);
	}
	
	public List<Circle> getMyFriendCircles(Map<String,Object> map){
		return circleMapper.selectFriendCricles(map);
	}
	
}
