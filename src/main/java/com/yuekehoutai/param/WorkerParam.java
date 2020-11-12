package com.yuekehoutai.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerParam {
    private Integer id;

    private String name;

    private String tel;

    private String password;

    private String idCard;
    private Integer cId;
}
