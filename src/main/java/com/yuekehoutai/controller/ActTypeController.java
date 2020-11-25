package com.yuekehoutai.controller;


import com.yuekehoutai.domain.ActType;
import com.yuekehoutai.domain.param.ActTypeParam;
import com.yuekehoutai.service.ActTypeService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@RestController
@Api(tags = "活动类型")
@RequestMapping("/actType")
public class ActTypeController {
    @Resource
    private ActTypeService actTypeService;
    @ApiOperation("查询所有活动类型")
    @GetMapping("selectAll")
    public JsonResult selectAll(){
        return new JsonResult(200,"success",actTypeService.list(),null);
    }

    @ApiOperation("新增活动类型")
    @ApiImplicitParam(name = "name",value = "活动名称")
    @PostMapping("insert")
    public JsonResult insert(String name){
        ActType actType = new ActType();
        actType.setName(name);
        if(actTypeService.save(actType)){
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(500,"服务器错误，请联系管理员",null,null);
        }
    }

    @ApiOperation("修改活动类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "类型ID"),
            @ApiImplicitParam(name = "name",value = "活动名称")
    })
    @PutMapping("update")
    public JsonResult update(@Valid ActTypeParam param)throws Exception{
        if(param==null){
            return new JsonResult(500,"参数为空",null,null);
        }else{
            ActType actType = new ActType();
            actType.setId(param.getId());
            actType.setName(param.getName());
            actTypeService.updateById(actType);
            return new JsonResult(200,"success",null,null);
        }
    }

}

