package top.cxh.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.QrImage;
import top.cxh.chat.bean.QrImageExample;
import top.cxh.chat.bean.QrImageExample.Criteria;
import top.cxh.chat.mapper.QrImageMapper;

@Service
public class QrImageService {

	@Autowired
	QrImageMapper qrImageMapper;
	
	/**
	 * 添加二维码
	 * @param qrImage
	 * @return
	 */
	public boolean addQrImage(QrImage qrImage) {
		return qrImageMapper.insertSelective(qrImage) != 0;
	}
	/**
	 * 删除二维码
	 * @param id
	 * @return
	 */
	public boolean delQrImage(Integer id) {
		return qrImageMapper.deleteByPrimaryKey(id) != 0;
	}
	
	/**
	 * 修改信息
	 * @param qrImage
	 * @return
	 */
	public boolean updateQrImage(QrImage qrImage) {
		return qrImageMapper.updateByPrimaryKeySelective(qrImage) != 0;
	}
	
	/**
	 * 获取二维码
	 * @param account
	 * @return
	 */
	public List<QrImage> getQrImage(String account){
		QrImageExample ex = new QrImageExample();
		Criteria cr = ex.createCriteria();
		cr.andAccountEqualTo(account);
		return qrImageMapper.selectByExample(ex);
	}
}
