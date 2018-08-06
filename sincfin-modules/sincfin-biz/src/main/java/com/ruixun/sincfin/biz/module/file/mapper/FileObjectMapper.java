package com.ruixun.sincfin.biz.module.file.mapper;

import com.ruixun.sincfin.domain.dto.FileObjectDto;
import com.ruixun.sincfin.domain.entity.FileObjectEntity;
import com.ruixun.sincfin.domain.query.FileObjectQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileObjectMapper {
    int insert(FileObjectEntity record);

    int insertSelective(FileObjectEntity record);

    FileObjectEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileObjectEntity record);

    int updateByPrimaryKey(FileObjectEntity record);

    FileObjectDto getByFileKey(@Param("fileKey") String fileKey);

    List<FileObjectDto> listByCondition(FileObjectQuery query);

}