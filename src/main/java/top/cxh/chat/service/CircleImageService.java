package top.cxh.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.CircleImage;
import top.cxh.chat.bean.CircleImageExample;
import top.cxh.chat.bean.CircleImageExample.Criteria;
import top.cxh.chat.mapper.CircleImageMapper;

@Service
public class CircleImageService {

	@Autowired
	CircleImageMapper circleImageMapper;
	
	/**
	 * 添加动态图片
	 * @param circleImages
	 * @return
	 */
	public boolean addCircleImages(List<CircleImage> circleImages) {
		return circleImageMapper.insertCircleImages(circleImages) != 0;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean delCircleImages(List<Integer> ids) {
		CircleImageExample ex = new CircleImageExample();
		Criteria cr = ex.createCriteria();
		cr.andIdIn(ids);
		return circleImageMapper.deleteByExample(ex) != 0;
	}
	
}
