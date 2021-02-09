package com.example.demo.oracle.temp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TempMapper {
    @Select("select * from temp_table where id = #{id}")
    Temp find(@Param("id") final long id);

    @Insert("insert into temp_table values (#{id})")
    void save(@Param("id") final long id);
}
