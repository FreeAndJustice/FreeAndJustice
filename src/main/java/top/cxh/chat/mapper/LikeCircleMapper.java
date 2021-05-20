package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.LikeCircle;
import top.cxh.chat.bean.LikeCircleExample;

import java.util.List;
@Repository
public interface LikeCircleMapper {
    long countByExample(LikeCircleExample example);

    int deleteByExample(LikeCircleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LikeCircle record);

    int insertSelective(LikeCircle record);

    List<LikeCircle> selectByExample(LikeCircleExample example);

    LikeCircle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LikeCircle record, @Param("example") LikeCircleExample example);

    int updateByExample(@Param("record") LikeCircle record, @Param("example") LikeCircleExample example);

    int updateByPrimaryKeySelective(LikeCircle record);

    int updateByPrimaryKey(LikeCircle record);
}