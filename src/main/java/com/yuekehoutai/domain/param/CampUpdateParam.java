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
public class CampUpdateParam {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String description;
    @NotNull
    private Integer cityId;
    @NotNull
    private String location;

    private MultipartFile[] files;
    @NotNull
    private String tel;
}
