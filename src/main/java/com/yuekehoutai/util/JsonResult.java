package com.yuekehoutai.util;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JsonResult {
    private Integer code;
    private String msg;
    private List<?> list;
    private Object obj;
}
