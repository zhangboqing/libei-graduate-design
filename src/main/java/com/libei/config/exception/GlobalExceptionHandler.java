package com.libei.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangboqing
 * @date 2018/3/29
 */
@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, String> jsonExceptionHandler(HttpServletRequest req, Exception e) {

        Map<String, String> re = new HashMap<String, String>();
        re.put("error", "1");
        re.put("msg", e.getMessage());
        return re;
    }

}
