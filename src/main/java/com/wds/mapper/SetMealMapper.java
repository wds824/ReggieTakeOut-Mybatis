package com.wds.mapper;

import com.wds.dto.SetmealDto;
import com.wds.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-28 15:08
 */
@Mapper
public interface SetMealMapper {
    public int count();

    List<SetmealDto> getPage(int start, int pageSize, String name);

    SetmealDto getById(Long id);

    void updateById(SetmealDto dto);

    List<Setmeal> getList(Long categoryId);
}
