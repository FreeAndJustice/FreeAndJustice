package top.cxh.chat.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.cxh.chat.bean.QrImage;
import top.cxh.chat.bean.QrImageExample;

import java.util.List;
@Repository
public interface QrImageMapper {
    long countByExample(QrImageExample example);

    int deleteByExample(QrImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QrImage record);

    int insertSelective(QrImage record);

    List<QrImage> selectByExample(QrImageExample example);

    QrImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QrImage record, @Param("example") QrImageExample example);

    int updateByExample(@Param("record") QrImage record, @Param("example") QrImageExample example);

    int updateByPrimaryKeySelective(QrImage record);

    int updateByPrimaryKey(QrImage record);
}