package com.yuekehoutai.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationParam {
    private Integer id;

    private String title;

    private String description;

    private MultipartFile[] images;
    private Integer pageNow;
    private Integer pageSize;
}
