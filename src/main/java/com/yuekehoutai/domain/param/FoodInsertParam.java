package com.yuekehoutai.domain.param;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodInsertParam {
    private Integer id;

    private String name;

    private Integer cId;

    private Double price;

    private String address;

    private Integer number;

    private String description;

    private Integer sales;

    private Integer typ;

    private Integer cityId;

    private String image;
}
