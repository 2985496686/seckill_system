package com.pojo;

import java.util.Date;

public class SuccessSeckilled {

    private  Long seckillId;

    private  Long userPhone;

    private  Byte state;

    private Date createTime;

    private Seckill seckill;

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public void setCreateTime(Date creatTime) {
        this.createTime = creatTime;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public Byte getState() {
        return state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    @Override
    public String toString() {
        return "SuccessSeckilledMapper{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }

    public SuccessSeckilled() {
    }

    public SuccessSeckilled(Long seckillId, Long userPhone, Byte state, Date createTime, Seckill seckill) {
        this.seckillId = seckillId;
        this.userPhone = userPhone;
        this.state = state;
        this.createTime = createTime;
        this.seckill = seckill;
    }
}
