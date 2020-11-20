package com.yuekehoutai.domain.param;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Integer page;

    private MultipartFile[] file;
}
