package top.cxh.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.CommentCircle;
import top.cxh.chat.mapper.CommentCircleMapper;

@Service
public class CommentCircleService {

	@Autowired
	CommentCircleMapper CommentCircleMapper;
	
	/**
	 * 添加一条评论记录
	 * @param commentCircle
	 * @return
	 */
	public boolean addCommentCircle(CommentCircle commentCircle) {
		return CommentCircleMapper.insertSelective(commentCircle) != 0;
	}
	
	/**
	 * 删除一条评论记录
	 * @param id
	 * @return
	 */
	public boolean delCommentCircle(Integer id) {
		return CommentCircleMapper.deleteByPrimaryKey(id) != 0;
	}
}
