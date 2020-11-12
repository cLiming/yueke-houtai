package com.yuekehoutai.param;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CarParam {
    private Integer id;

    private String type;

    private String name;

    private String description;

    private String image;

    private Integer cId;

    private Integer uId;

    private Double preprice;

    private Double price;

    private Integer status;

    private Integer typ;
    private String endTime;
    private Integer pageNow;
    private Integer pageSize;
}
