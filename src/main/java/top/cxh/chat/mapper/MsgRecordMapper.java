package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.MsgRecord;
import top.cxh.chat.bean.MsgRecordExample;

import java.util.List;
@Repository
public interface MsgRecordMapper {
    long countByExample(MsgRecordExample example);

    int deleteByExample(MsgRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MsgRecord record);

    int insertSelective(MsgRecord record);

    List<MsgRecord> selectByExample(MsgRecordExample example);
    
    List<MsgRecord> selectMyReqMsgByAccount(String account);
    
    List<MsgRecord> selectMyGroupReqMsgByAccount(@Param("groupId") String groupId);

    MsgRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MsgRecord record, @Param("example") MsgRecordExample example);

    int updateByExample(@Param("record") MsgRecord record, @Param("example") MsgRecordExample example);

    int updateByPrimaryKeySelective(MsgRecord record);

    int updateByPrimaryKey(MsgRecord record);
}