package com.dto;

/*
秒杀地址的暴露
 */
public class Exposer {

    //是否秒杀
    private boolean expose;

    //秒杀地址加密
   private String md5;

   //秒杀商品id
   private long seckillId;

   //系统当前时间
    private long now;

    //秒杀开始时间
    private long start;

    //秒杀结束时间
    private long end;


    public Exposer(boolean expose, String md5, long seckillId) {
        this.expose = expose;
        this.md5 = md5;
        this.seckillId = seckillId;
    }


    public Exposer(boolean expose, long seckillId,long now, long start, long end) {
        this.expose = expose;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean expose, long seckillId) {
        this.expose = expose;
        this.seckillId = seckillId;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public boolean isExpose() {
        return expose;
    }

    public void setExpose(boolean expose) {
        this.expose = expose;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
