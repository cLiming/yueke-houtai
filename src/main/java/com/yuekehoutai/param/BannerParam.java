package com.yuekehoutai.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerParam {
    private Integer id;

    private Integer vId;

    private Integer status;
    private String image;
    private Integer pageNow;
    private Integer pageSize;
}
