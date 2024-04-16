package com.whn.hellospring.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * DO，DOT模型转换
 */
public class DO2DTOUtil {

    private static DO2DTOUtil instance = new DO2DTOUtil();
    private DO2DTOUtil(){}
    public static DO2DTOUtil getInstance(){
        return instance;
    }

    /**
     * DTO集合转换成DO集合
     *
     * @param dtoList DTO 对象集合
     * @param doClass DO 类型
     * @return doClass类型的集合
     */
    public Object dtoListToDoList(Object dtoList, Class doClass) {
        if (dtoList == null) {
            return null;
        }
        List<Object> doList = new ArrayList<>();
        for (Object dto : (List) dtoList) {
            Object i = dtoToDo(dto, doClass);
            if (i != null) {
                doList.add(i);
            }
        }
        return doList;
    }

    /**
     * DTO模型转换成DO
     *
     * @param objectDto DTO 对象
     * @param doClass   DO 类型
     * @return doClass类型的对象
     */
    public Object dtoToDo(Object objectDto, Class doClass) {
        if (objectDto == null) {
            return null;
        }
        Object objectDo = null;
        try {
            objectDo = doClass.newInstance();
            BeanUtils.copyProperties(objectDto, objectDo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objectDo;
    }


    /**
     * DO集合转换成DTO集合
     *
     * @param doList   DO 对象集合
     * @param dtoClass DTO 类型
     * @return dtoClass类型的集合
     */
    public Object doListToDtoList(Object doList, Class dtoClass) {
        if (doList == null) {
            return null;
        }
        List<Object> dtoList = new ArrayList<>();
        for (Object i : (List) doList) {
            Object dto = doToDto(i, dtoClass);
            if (dto != null) {
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    /**
     * DO转换成DTO
     *
     * @param objectDo DO 对象
     * @param dtoClass DTO 类型
     * @return dtoClass类型的对象
     */
    public Object doToDto(Object objectDo, Class dtoClass) {
        if (objectDo == null) {
            return null;
        }
        Object objectDto = null;
        try {
            objectDto = dtoClass.newInstance();
            BeanUtils.copyProperties(objectDo, objectDto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objectDto;
    }

}
