package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Camp;
import com.yuekehoutai.domain.param.CampListParam;
import com.yuekehoutai.domain.param.CampUpdateParam;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.service.CampService;
import com.yuekehoutai.service.CityService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.ValidationException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author corazon
 * @since 2020-11-10
 */
@RestController
@Api(tags = "营地")
@RequestMapping("/camp")
public class CampController {
    @Resource
    private CampService campService;
    @Resource
    private CityService cityService;

    @ApiOperation("查询所有加盟的营地申请")
    @GetMapping("selectAllCheck")
    public JsonResult selectAllCheck(){
        return new JsonResult(200,"success",campService.selectAllCheck(),null);
    }

    @ApiOperation("设置热门营地")
    @ApiImplicitParam(name = "id",value = "审核营地id")
    @PostMapping("check")
    public JsonResult check(Integer id){
        boolean tag = campService.campstatusUpdate(id,1);
        if (tag) {
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(500,"服务器错误",null,null);
        }
    }

    //通过城市ID,营地名称,营地状态查询符合条件的所有营地
    @ApiOperation("条件查询营地")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityId",value = "城市ID",required=true),
            @ApiImplicitParam(name = "name",value = "营地名称"),
            @ApiImplicitParam(name = "status",value = "营地状态0热门 1普通 2待审核",required=true),
            @ApiImplicitParam(name = "pageIndex",value = "当前页码",required=true),
            @ApiImplicitParam(name = "pageNum",value = "每页数据条数",required=true)
    })
    @GetMapping("select")
    public JsonResult campList(@Valid CampListParam campListParam) throws Exception {
        return new JsonResult(200, "success", null, campService.campListLimit(campListParam));
    }

    //审核营地
    @ApiOperation("审核营地")
    @ApiImplicitParam(name = "id",value = "审核营地id")
    @PostMapping("update")
    public JsonResult campUpdate(Integer id){
        boolean tag = campService.campstatusUpdate(id,0);
        if (tag) {
            return new JsonResult(200,"success",null,null);
        }else {
            return new JsonResult(500,"服务器错误",null,null);
        }
    }


    @PutMapping("update")
    @ApiOperation("更新营地信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "营地ID",required = true),
            @ApiImplicitParam(name = "name",value = "营地名称",required=true),
            @ApiImplicitParam(name = "address",value = "营地地址",required=true),
            @ApiImplicitParam(name = "description",value = "营地描述",required=true),
            @ApiImplicitParam(name = "cityId",value = "所选城市ID",required=true),
            @ApiImplicitParam(name = "location",value = "营地位置详细经纬度",required=true),
            @ApiImplicitParam(name = "tel",value = "营地联系电话",required=true),
            @ApiImplicitParam(name = "files",value = "上传图片,只能上传PNG或JPG格式",dataType = "file",paramType = "form")
    })
    public JsonResult updateCamp(CampUpdateParam param)throws Exception{
        Camp camp = new Camp();
        BeanUtils.copyProperties(param,camp);
        if(param.getFiles()!=null){
            for (int i = 0;i< param.getFiles().length;i++){
                if(param.getFiles()[i]==null){
                    throw  new ProjectException(1101,"图片不能为空");
                }
                if (!(param.getFiles()[i].getOriginalFilename().endsWith(".jpg")||param.getFiles()[i].getOriginalFilename().endsWith(".png"))) {
                    throw new ProjectException(1102, "图片格式错误,只能上传jpg或者png格式图片");
                }
            }
            boolean tag = campService.updateCamp(camp,param.getFiles());
            if(tag){
                return new JsonResult(200,"success",null,null);
            }else {
                throw new ProjectException(500,"服务器错误，请联系管理员");
            }
        }else{
            campService.updateById(camp);
            return new JsonResult(200,"success",null,null);
        }
    }



    //单选删除营地图片
    @DeleteMapping("deleteImage")
    @ApiOperation("单选删除营地图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "营地id",required = true),
            @ApiImplicitParam(name = "image",value = "图片地址",required = true)

    })
    public JsonResult deleteImage(Integer id,String image)throws Exception{
        if(id==null||image.isEmpty()){
            throw new ValidationException("参数错误");
        }
        if(campService.removeImage(id, image)){
            return new JsonResult(200,"success",null,null);
        }else {
            throw new ProjectException(500,"删除失败");
        }
    }

    //通过ID删除所有营地图片
    @DeleteMapping("deleteImageAll")
    @ApiOperation("删除所选营地的所有图片")
    @ApiImplicitParam(name = "id",value = "营地id",required = true)
    public JsonResult deleteImageAll(Integer id)throws Exception{
        if(id==null){
            throw new ValidationException("参数错误");
        }
        if(campService.removeImages(id)){
            return new JsonResult(200,"success",null,null);
        }else {
            throw new ProjectException(500,"删除失败");
        }
    }

    @ApiOperation("查询所有城市")
    @GetMapping("cityList")
    public JsonResult cityList()throws Exception{
        return new JsonResult(200,"success",cityService.list(),null);
    }


}

