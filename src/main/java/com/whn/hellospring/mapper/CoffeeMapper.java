package com.whn.hellospring.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface CoffeeMapper {

//    @Insert("INSERT into t_coffee (name,price,create_time,update_time)"
//    +"values(#{name},#{price},now(),now())")
//    @Options(useGeneratedKeys = true)
//    int save(Coffee coffee);
//
//
//    @Select("select * from t_coffee where id = #{id}")
//    @Results({
//            @Result(id = true,column = "id",property = "id"),
//            @Result(column = "create_time",property = "create_time"),
//    })
//    Coffee findById(@Param("id") Long id);
//
//
//
//    //分页
//    @Select("select * from t_coffee order by id")
//    List<Coffee> findAllWithRowBounds(RowBounds rowBounds);
//
//    @Select("select * from t_coffee order by id")
//    List<Coffee> findAllWithParam(@Param("pageNum") int pageNum,
//                                  @Param("pageSize") int pageSize);





}
