package com.yuekehoutai.domain.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodListParam {
    @NotNull
    private Integer cityId;

    private String name;

    private Integer cId;
    @NotNull
    private Double priceType;
    @NotNull
    private Integer saleType;
    @NotNull
    private Integer pageIndex;
    @NotNull
    private Integer pageNum;

}
