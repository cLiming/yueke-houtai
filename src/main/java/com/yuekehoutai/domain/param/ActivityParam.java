package com.yuekehoutai.domain.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityParam {
    @NotNull
    private String name;
    @NotNull
    private Integer number;
    @NotNull
    private Double price;
    @NotNull
    private String description;
    @NotNull
    private Integer cId;
    @NotNull
    private Integer actTypeId;

    @NotNull
    private Integer cityId;

    private MultipartFile[] files;


}
