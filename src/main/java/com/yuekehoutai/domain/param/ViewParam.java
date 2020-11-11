package com.yuekehoutai.domain.param;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class ViewParam {
    private Integer id;

    private String name;

    private String description;

    private String address;

    private Double price;

    private String time;

    private Integer cId;

    private String location;

    private String image;

    private Integer page;
}
