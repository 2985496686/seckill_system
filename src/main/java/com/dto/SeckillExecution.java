package com.dto;

import com.enums.SeckillStateEnum;
import com.pojo.SuccessSeckilled;

public class SeckillExecution {

    private long seckillId;

    private int  state;

    //对秒杀状态的描述
    private String stateInfo;

    //秒杀成功的对象
    private SuccessSeckilled successSeckilled;

    //秒杀成功时返回全部信息
    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessSeckilled successSeckilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successSeckilled = successSeckilled;
    }

    //秒杀失败
    public SeckillExecution(long seckillId,SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo =stateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSeckilled getSuccessSeckilled() {
        return successSeckilled;
    }

    public void setSuccessSeckilled(SuccessSeckilled successSeckilled) {
        this.successSeckilled = successSeckilled;
    }
}
