package com.yuekehoutai.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {
    private Integer id;

    private String name;

    private String resources;

    private Integer pId;
    private List<MenuDto> children;
}
