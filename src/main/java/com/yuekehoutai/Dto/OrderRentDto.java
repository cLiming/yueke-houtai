package com.yuekehoutai.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRentDto {
    private Integer id;

    private LocalDateTime orderTime;

    private Integer status;

    private Double totals;

    private Integer uId;
    private List<OrderRentDetailDto> orderRentDetailDtos;
}
