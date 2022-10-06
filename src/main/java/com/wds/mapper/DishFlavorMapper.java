package com.wds.mapper;

import com.wds.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-26 16:54
 */
@Mapper
public interface DishFlavorMapper {

    List<DishFlavor> getFlavorsByDishId(Long id);

    void removeFlavorsByDishId(Long id);

    void insertFlavors(@Param("flavors") List<DishFlavor> flavors
            , @Param("dishId") Long id, @Param("now") Date date, @Param("user") Long empId);


    void deleteByDishIds(List<Long> list);
}
