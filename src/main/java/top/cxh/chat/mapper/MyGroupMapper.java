package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.MyGroup;
import top.cxh.chat.bean.MyGroupExample;

import java.util.List;
@Repository
public interface MyGroupMapper {
    long countByExample(MyGroupExample example);

    int deleteByExample(MyGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyGroup record);

    int insertSelective(MyGroup record);

    List<MyGroup> selectByExample(MyGroupExample example);

    MyGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyGroup record, @Param("example") MyGroupExample example);

    int updateByExample(@Param("record") MyGroup record, @Param("example") MyGroupExample example);

    int updateByPrimaryKeySelective(MyGroup record);

    int updateByPrimaryKey(MyGroup record);
}