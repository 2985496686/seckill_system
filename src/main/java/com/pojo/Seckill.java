package com.pojo;


import java.util.Date;

public class Seckill {
    private Long seckillId;

    private String name;

    private Integer number;

    private Date createTime;

    private Date startTime;

    private  Date endTime;

    public Seckill(Long seckillId, String name, Integer number, Date createTime, Date startTime, Date endTime) {
        this.seckillId = seckillId;
        this.name = name;
        this.number = number;
        this.createTime = createTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Seckill() {
    }

    public void setSeckillId(Long seckillId) {
        seckillId = seckillId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
