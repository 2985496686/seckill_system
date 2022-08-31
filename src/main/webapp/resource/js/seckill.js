
var seckill = {
    //封装ajax请求的url，便于后期维护
    url:{
        now:function () {
            return "/seckill_system/seckill/time";
        },
        exposer:function (seckillId) {
            return "/seckill_system/seckill/" + seckillId +"/exposer";
        },
        execute:function (seckillId,md5) {
            return "/seckill_system/seckill/" + seckillId + "/" + md5 + "/execute";
        },
        excuteByProcedure:d=function (seckillId, md5) {
            return "/seckill_system/seckill/" + seckillId + "/" + md5 + "/executeByProcedure";
        }
    },
    //验证手机号存在，并且有效
    validatePhone:function(phone){
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }
        return false;
    },
    countDown:function(seckillId,nowTime,startTime,endTime){
        var seckillBox = $('#seckill-box');
        //秒杀未开始
        if(nowTime < startTime){
            seckillBox.countdown(startTime + 1000,function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on("finish",function () {
                //时间到时执行该方法
                seckill.handleSeckill();
            })
        }else if(nowTime > endTime){
            seckillBox.html("秒杀结束");
        }else{
            //秒杀开始
            seckill.handleSeckill(seckillId,seckillBox);
        }
    },
    handleSeckill:function(seckillId,seckillBox){
        seckillBox.html("<button class='btn btn-primary btn-lg' id='seckillBtn'>开始秒杀</button>");
        //暴露秒杀接口
        $.post(seckill.url.exposer(seckillId),{},function (result) {
            if(result && result['success']){
                var exposer = result['data'];
                if(exposer && exposer['expose']){
                    //秒杀接口暴露成功
                    //使用这种方式来绑定点击事件，该事件只会绑定一次，防止大量重复请求发送给服务器
                    $('#seckillBtn').one('click',function () {
                        //禁用按钮
                        $(this).addClass("disabled");
                        //执行秒杀
                        $.post(seckill.url.excuteByProcedure(seckillId,exposer['md5']),{},function (result) {
                            if(result && result['success']){
                                var execution = result['data'];
                                var stateInfo = execution['stateInfo'];
                                seckillBox.html("<label class='label label-success'>" + stateInfo+"</label>");
                            }
                        })
                        seckillBox.show();
                    })
                }else{
                    //接口暴露失败，说明用户显示时间与系统实际时间有偏差，需重新计算
                    var startTime = exposer['startTime'];
                    var endTime = exposer['endTime'];
                    var nowTime = exposer['nowTime'];
                    seckill.countDown(seckillId,nowTime,startTime,endTime);
                }
            }
        })

    },
    detail:{
        init:function(params){
            //从cookie中获取手机号码
            var userPhone = $.cookie('userPhone');
            if(!seckill.validatePhone(userPhone)){
                //绑定弹出层
                var seckillPhoneModel = $('#killPhoneModal');
                    seckillPhoneModel.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });
                //点击提交按钮
                if($('#killPhoneBtn').click(function () {
                    //获取填入的手机号
                    var inputPhone = $('#killPhoneKey').val();
                    if(seckill.validatePhone(inputPhone)){
                        $.cookie('userPhone',inputPhone,{expires:7,path:"/seckill_system"});
                        //保存成功，刷新页面
                        window.location.reload();
                    }else{
                        //错误文案信息抽取到前端字典里
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                }));
            }

            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(seckill.url.now(),{},function(result){
                if(result && result['success']){
                    var nowTime = result['data'];
                    //时间判断，计时交互
                    seckill.countDown(seckillId,nowTime,startTime,endTime);
                }else{
                    alert('未获取到参数！');
                }
            })
        }
    }
}