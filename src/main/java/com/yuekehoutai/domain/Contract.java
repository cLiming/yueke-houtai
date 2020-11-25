package com.yuekehoutai.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author corazon
 * @since 2020-11-21
 */
@TableName("t_contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer ownerId;

    private String status;

    private Integer carId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer days;

    private Double price;

    private Double totals;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotals() {
        return totals;
    }

    public void setTotals(Double totals) {
        this.totals = totals;
    }

    @Override
    public String toString() {
        return "Contract{" +
        "id=" + id +
        ", ownerId=" + ownerId +
        ", status=" + status +
        ", carId=" + carId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", days=" + days +
        ", price=" + price +
        ", totals=" + totals +
        "}";
    }
}
