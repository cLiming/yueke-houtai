package com.yuekehoutai.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampContractParam {
    private  int id;

    private Integer campId;

    private Integer workerId;

    private String images;

    private Integer status;

    private Integer cost;
}
