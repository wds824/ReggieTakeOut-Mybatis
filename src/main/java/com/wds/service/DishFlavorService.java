package com.wds.service;

import com.wds.dto.DishDto;
import com.wds.entity.DishFlavor;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-26 16:55
 */
public interface DishFlavorService  {
    List<DishFlavor> getFlavors(Long id);

    void updateFlavors(DishDto dto);

    void saveFlavorsFromDto(DishDto dto);

    void removeByDishId(List<Long> list);
}
