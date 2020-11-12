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
public class ActListParam {
    @NotNull(message = "必须选择城市")
    private Integer cityId;
    @NotNull
    private Integer type;
    private String  name;
    @NotNull
    private Integer priceType;
    private Integer cId;
    @NotNull
    private Integer pageIndex;
    @NotNull
    private Integer pageNum;
}
