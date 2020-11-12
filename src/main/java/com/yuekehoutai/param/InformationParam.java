package com.yuekehoutai.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationParam {
    private Integer id;

    private String title;

    private String description;

    private String image;
    private Integer pageNow;
    private Integer pageSize;
}
