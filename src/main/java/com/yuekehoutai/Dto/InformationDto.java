package com.yuekehoutai.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationDto {
    private Integer id;

    private String title;

    private String description;

    private String image;
}
