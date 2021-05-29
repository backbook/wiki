package com.backbook.wiki.config;

import com.backbook.wiki.resp.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 统一异常处理，数据预处理
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {


    /**
     *
     * @param e
     * @return
     */

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp ValidExceptionHandler(BindException e){
        CommonResp<Object> commonResp = new CommonResp<>();
        log.warn("参数检验吃白：{}",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }



}
