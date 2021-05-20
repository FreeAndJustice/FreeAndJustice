package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.Circle;
import top.cxh.chat.bean.CircleExample;

import java.util.List;
import java.util.Map;
@Repository
public interface CircleMapper {
    long countByExample(CircleExample example);

    int deleteByExample(CircleExample example);

    int deleteByPrimaryKey(String circleId);

    int insert(Circle record);

    int insertSelective(Circle record);

    List<Circle> selectByExample(CircleExample example);
    
    List<Circle> selectFriendCricles(Map<String,Object> map);

    Circle selectByPrimaryKey(String circleId);

    int updateByExampleSelective(@Param("record") Circle record, @Param("example") CircleExample example);

    int updateByExample(@Param("record") Circle record, @Param("example") CircleExample example);

    int updateByPrimaryKeySelective(Circle record);

    int updateByPrimaryKey(Circle record);
}