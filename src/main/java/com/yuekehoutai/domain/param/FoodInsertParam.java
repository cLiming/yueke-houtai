package com.yuekehoutai.domain.param;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class FoodInsertParam {
    @NotNull
    private String name;
    @NotNull
    private Integer cId;
    @NotNull
    private Double price;
    @NotNull
    private String address;
    @NotNull
    private Integer number;
    @NotNull
    private String description;

    private Integer typ;
    @NotNull
    private Integer cityId;

    @NotNull
    private MultipartFile[] files;
}
