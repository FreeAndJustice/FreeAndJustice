package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.CircleImage;
import top.cxh.chat.bean.CircleImageExample;

import java.util.List;
@Repository
public interface CircleImageMapper {
    long countByExample(CircleImageExample example);

    int deleteByExample(CircleImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CircleImage record);

    int insertSelective(CircleImage record);
    
    int insertCircleImages(List<CircleImage> circleImages);

    List<CircleImage> selectByExample(CircleImageExample example);

    CircleImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CircleImage record, @Param("example") CircleImageExample example);

    int updateByExample(@Param("record") CircleImage record, @Param("example") CircleImageExample example);

    int updateByPrimaryKeySelective(CircleImage record);

    int updateByPrimaryKey(CircleImage record);
}