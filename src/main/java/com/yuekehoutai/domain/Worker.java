package com.yuekehoutai.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_worker")
public class Worker implements Serializable {

    private Integer id;

    private String name;

    private String tel;

    private String password;

    private String idCard;

    private Integer cId;
}
