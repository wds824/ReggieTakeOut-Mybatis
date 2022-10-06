package com.wds.dto;

import com.wds.entity.Dish;
import com.wds.entity.DishFlavor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-26 9:36
 */
@Getter
@Setter
public class DishDto extends Dish{

    private String categoryName;
    private List<DishFlavor> flavors;
    private Integer copies;
}
