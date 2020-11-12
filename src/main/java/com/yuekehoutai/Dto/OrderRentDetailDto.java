package com.yuekehoutai.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRentDetailDto {
    private Integer id;

    private Integer oId;

    private Integer carId;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer days;
}
