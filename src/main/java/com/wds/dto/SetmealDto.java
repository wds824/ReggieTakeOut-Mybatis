package com.wds.dto;

import com.wds.entity.Setmeal;
import com.wds.entity.SetmealDish;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 19:01
 */
@Getter
@Setter
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;

    private String categoryName;
}

