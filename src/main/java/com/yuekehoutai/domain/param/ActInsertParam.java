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
public class ActInsertParam {
    private Integer id;

    private String name;

    private Integer number;

    private Double price;

    private String description;

    private Integer cId;

    private Integer actTypeId;

    private Integer typ;

    private Integer cityId;
}
