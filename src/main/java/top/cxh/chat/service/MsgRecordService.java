package top.cxh.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.cxh.chat.bean.MsgRecord;
import top.cxh.chat.bean.MsgRecordExample;
import top.cxh.chat.bean.MsgRecordExample.Criteria;
import top.cxh.chat.mapper.MsgRecordMapper;

@Service
public class MsgRecordService {

	@Autowired
	MsgRecordMapper msgRecordMapper;
	
	/**
	 * 添加消息记录
	 * @param msgRecord
	 * @return
	 */
	public boolean addMsgRecord(MsgRecord msgRecord) {
		return msgRecordMapper.insertSelective(msgRecord) != 0;
	}
	
	/**
	 * 修改消息状态
	 * @param msgRecord
	 * @return
	 */
	public boolean updateMsgState(MsgRecord msgRecord) {
		return msgRecordMapper.updateByPrimaryKeySelective(msgRecord) != 0;
	}
	
	/**
	 * 检查是否已存在未读的申请记录
	 * @param fromAccount
	 * @param toAccount
	 * @return
	 */
	public boolean isExistReqMsg(String fromAccount,String toAccount) {
		MsgRecordExample ex = new MsgRecordExample();
		Criteria cr = ex.createCriteria();
		cr.andFromAccountEqualTo(fromAccount);
		cr.andToAccountEqualTo(toAccount);
		cr.andMsgTypeEqualTo("req");
		cr.andMsgStateEqualTo(0);
		return msgRecordMapper.selectByExample(ex).size() != 0;
	}
	
	/**
	 * 查询给我的申请消息
	 * @param account
	 * @return
	 */
	public List<MsgRecord> getReqMsgRecord(String account){
		return msgRecordMapper.selectMyReqMsgByAccount(account);
	}
	
	/**
	 * 获取加群验证消息
	 * @param groupId
	 * @return
	 */
	public List<MsgRecord> getGroupReqMsgRecord(String groupId){
		return msgRecordMapper.selectMyGroupReqMsgByAccount(groupId);
	}
	
	/**
	 * 获取离线状态的未读消息
	 * @param account
	 * @return
	 */
	public List<MsgRecord> getOfflineMsg(String account){
		MsgRecordExample ex = new MsgRecordExample();
		Criteria cr = ex.createCriteria();
		cr.andToAccountEqualTo(account);
		cr.andMsgStateEqualTo(0);
		cr.andMsgTypeEqualTo("chat");
		return msgRecordMapper.selectByExample(ex);
	}
	
}
