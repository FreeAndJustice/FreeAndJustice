package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.Friends;
import top.cxh.chat.bean.FriendsExample;

import java.util.List;
import java.util.Map;
@Repository
public interface FriendsMapper {
    long countByExample(FriendsExample example);

    int deleteByExample(FriendsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Friends record);

    int insertSelective(Friends record);

    List<Friends> selectByExample(FriendsExample example);
    
    List<Friends> selectMyFriendsByAccount(String account);
    
    List<Friends> selectMyCirclesByAccount(String account);
    
    List<Friends> selectMyFriendsByKeys(Map<String,Object> map);

    Friends selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByExample(@Param("record") Friends record, @Param("example") FriendsExample example);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);
    
    int updateByMyExample(Map<String,Object> map);
}