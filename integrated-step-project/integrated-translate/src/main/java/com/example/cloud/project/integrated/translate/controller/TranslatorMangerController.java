package com.example.cloud.project.integrated.translate.controller;

import com.example.cloud.project.integrated.common.domain.R;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.common.domain.constant.BizExceptionEnum;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.translate.service.TranslatorMangerService;
import com.example.cloud.project.integrated.translate.utils.ObjectTolls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/18 16:13
 */
@RestController
@Slf4j
@RequestMapping("/translator/manager/v1")
public class TranslatorMangerController {

    @Autowired
    private TranslatorMangerService service;

    @PostMapping("/proxy")
    public R<TranslateResponse> translate(@RequestBody RemoteTranslateRequest body){
        try{
            TranslateResponse response = service.translate(body);
            if(response == null){
                return R.error(BizExceptionEnum.INTERFACE_SYSTEM_ERROR);
            }
            response.setTarget(ObjectTolls.firstWordLower(response.getTarget()));
            return R.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return R.error(e.getMessage());
        }

    }

}
