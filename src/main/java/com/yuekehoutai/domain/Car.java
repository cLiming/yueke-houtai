package com.yuekehoutai.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author corazon
 * @since 2020-11-11
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@TableName("t_car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String type;

    private String name;

    private String description;

    private String image;

    private Integer cId;

    private Integer ownerId;

    private Double preprice;

    private Double price;

    private Integer status;

    private Integer typ;
    @TableField("endTime")
    private LocalDateTime endTime;



}
