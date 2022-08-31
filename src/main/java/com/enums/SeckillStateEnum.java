package com.enums;


public enum SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    REWRITE(-2,"秒杀数据篡改"),
    INNE_RERROR(-3,"系统异常");

    private int state;

    private  String stateInfo;


    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
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

    public static SeckillStateEnum stateOf(int state){
        for(SeckillStateEnum stateEnum:SeckillStateEnum.values()){
            if(stateEnum.state == state){
                return stateEnum;
            }
        }
        return null;
    }
}
