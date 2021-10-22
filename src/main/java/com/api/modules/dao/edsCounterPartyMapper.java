package com.api.modules.dao;

import com.api.modules.model.edsCounterParty;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

//添加此注解，便可以被扫描到
@Mapper
//@Resource
public interface edsCounterPartyMapper {
    int deleteByPrimaryKey(String counterpartyid);

    int insert(edsCounterParty record);

    int insertSelective(edsCounterParty record);

    edsCounterParty selectByPrimaryKey(String counterpartyid);

    int updateByPrimaryKeySelective(edsCounterParty record);

    int updateByPrimaryKey(edsCounterParty record);
}