package com.yuekehoutai.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRentDetailParam {
    private Integer id;

    private Integer oId;

    private Integer carId;

    private String name;

    private String startTime;

    private String endTime;

    private Integer days;
}
