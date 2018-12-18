package com.renbaojia.blog.dao;

import com.renbaojia.blog.model.Weather;
import com.renbaojia.blog.model.WeatherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeatherMapper {
    long countByExample(WeatherExample example);

    int deleteByExample(WeatherExample example);

    int insert(Weather record);

    int insertSelective(Weather record);

    List<Weather> selectByExample(WeatherExample example);

    int updateByExampleSelective(@Param("record") Weather record, @Param("example") WeatherExample example);

    int updateByExample(@Param("record") Weather record, @Param("example") WeatherExample example);
}