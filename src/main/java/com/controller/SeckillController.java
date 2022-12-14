package com.controller;

import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.dto.SeckillResult;
import com.enums.SeckillStateEnum;
import com.exeception.RepeatSeckillException;
import com.exeception.SeckillCloseException;
import com.pojo.Seckill;
import com.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取所有秒杀商品列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getSeckillList(Model model) {
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("seckillList", seckillList);
        return "list";
    }

    /**
     * 根据id获取商品
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String getSeckillById(@PathVariable Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckillId);
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * 暴露秒杀,ajax发送json对象
     */
    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable Long seckillId, Model model) {
        try {
            Exposer exposer = seckillService.exposeSeckillUrl(seckillId);
            return new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            return new SeckillResult<Exposer>(false, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/execute",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExecution> execute(@PathVariable Long seckillId,
                                                   @CookieValue(value = "userPhone",required = false) Long userPhone,
                                                   @PathVariable String md5) {
        if(userPhone == null){
            return new SeckillResult<SeckillExecution>(false,"手机号未注册");
        }
        try{
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (RepeatSeckillException r){
            return new SeckillResult<SeckillExecution>(true,new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL));
        }catch(SeckillCloseException s){
            return new SeckillResult<SeckillExecution>(true,new SeckillExecution(seckillId, SeckillStateEnum.END));
        }catch (Exception e){
            return new SeckillResult<SeckillExecution>(true,new SeckillExecution(seckillId, SeckillStateEnum.INNE_RERROR));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/time",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Long> time(){
        return new SeckillResult<Long>(true,new Date().getTime());
    }


    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/executeByProcedure",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExecution> executeByProcedure(@PathVariable Long seckillId,
                                                   @CookieValue(value = "userPhone",required = false) Long userPhone,
                                                   @PathVariable String md5) {
        try{
            SeckillExecution seckillExecution = seckillService.executeSeckillByProcedure(seckillId, userPhone, md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (Exception e){
            return new SeckillResult<SeckillExecution>(false,new SeckillExecution(seckillId,SeckillStateEnum.INNE_RERROR));
        }
    }
}
