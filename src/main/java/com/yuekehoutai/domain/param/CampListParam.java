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
public class CampListParam {
    @NotNull
    private Integer cityId;
    private String name;
    @NotNull
    private Integer status;
    @NotNull
    private Integer pageIndex;
    @NotNull
    private Integer pageNum;
}
