package top.cxh.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.LikeCircle;
import top.cxh.chat.bean.LikeCircleExample;
import top.cxh.chat.bean.LikeCircleExample.Criteria;
import top.cxh.chat.mapper.LikeCircleMapper;

@Service
public class LikeCircleService {

	@Autowired
	LikeCircleMapper likeCircleMapper;
	
	/**
	 * 增加点赞记录
	 * @param likeCircle
	 * @return
	 */
	public boolean addLikeCircle(LikeCircle likeCircle) {
		return likeCircleMapper.insertSelective(likeCircle) != 0;
	}
	
	/**
	 * 是否已点赞
	 * @param account
	 * @param circleId
	 * @return
	 */
	public boolean isLikeCircle(String account,String circleId) {
		LikeCircleExample ex = new LikeCircleExample();
		Criteria cr = ex.createCriteria();
		cr.andAccountEqualTo(account);
		cr.andCircleIdEqualTo(circleId);
		return likeCircleMapper.selectByExample(ex).size() != 0;
	}
	
	/**
	 * 删除点赞记录
	 * @param id
	 * @return
	 */
	public boolean delLikeCircle(Integer id) {
		return likeCircleMapper.deleteByPrimaryKey(id) != 0;
	}
	
}
