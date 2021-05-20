package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.CommentCircle;
import top.cxh.chat.bean.CommentCircleExample;

import java.util.List;
@Repository
public interface CommentCircleMapper {
    long countByExample(CommentCircleExample example);

    int deleteByExample(CommentCircleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommentCircle record);

    int insertSelective(CommentCircle record);

    List<CommentCircle> selectByExample(CommentCircleExample example);

    CommentCircle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommentCircle record, @Param("example") CommentCircleExample example);

    int updateByExample(@Param("record") CommentCircle record, @Param("example") CommentCircleExample example);

    int updateByPrimaryKeySelective(CommentCircle record);

    int updateByPrimaryKey(CommentCircle record);
}