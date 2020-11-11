package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.domain.View;
import com.yuekehoutai.domain.param.ViewParam;
import com.yuekehoutai.service.ViewService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/view")
public class ViewController {
    @Resource
    private ViewService viewService;
    //查询所有景点
    @GetMapping("selectAll")
    public JsonResult selectAll(ViewParam viewParam)throws Exception{
        Page<View> viewPage = viewService.selectAll(viewParam);
        return new JsonResult(200,"success",null,viewPage);
    }

    //新增景点
    @PostMapping("insertView")
    public JsonResult insertView(MultipartFile[] file,ViewParam viewParam)throws Exception{
        StringBuffer sb = new StringBuffer();
        String path = "view/";
        if(file.length>0){
            for(int i=0;i<file.length;i++){
                 String name = path+file[i].getOriginalFilename();
                 if(name.endsWith(".jpg")||name.endsWith(".png")){
                    sb.append(name);
                    if(i!=file.length-1){
                        sb.append(",");
                    }
                }else{
                    return new JsonResult(500,"文件规格不符合",null,null);
                }
            }
        }
        viewParam.setImage(sb.toString());
        viewService.insrtView(viewParam);
        return new JsonResult(200,"success",null,null);
    }

    //
}

