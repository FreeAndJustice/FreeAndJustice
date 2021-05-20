package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.GroupChat;
import top.cxh.chat.bean.GroupChatExample;

import java.util.List;
@Repository
public interface GroupChatMapper {
    long countByExample(GroupChatExample example);

    int deleteByExample(GroupChatExample example);

    int deleteByPrimaryKey(String groupId);

    int insert(GroupChat record);

    int insertSelective(GroupChat record);

    List<GroupChat> selectByExample(GroupChatExample example);
    
    List<GroupChat> selectBySearchKeys(@Param("keys") String keys);

    GroupChat selectByPrimaryKey(String groupId);

    int updateByExampleSelective(@Param("record") GroupChat record, @Param("example") GroupChatExample example);

    int updateByExample(@Param("record") GroupChat record, @Param("example") GroupChatExample example);

    int updateByPrimaryKeySelective(GroupChat record);

    int updateByPrimaryKey(GroupChat record);
}