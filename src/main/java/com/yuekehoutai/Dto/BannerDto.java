package com.yuekehoutai.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class BannerDto {
    private Integer id;

    private Integer vId;

    private Integer status;
    private String image;
}
