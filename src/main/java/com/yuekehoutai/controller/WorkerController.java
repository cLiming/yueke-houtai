package com.yuekehoutai.controller;


import com.yuekehoutai.domain.Worker;
import com.yuekehoutai.service.WorkerService;
import com.yuekehoutai.util.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "员工")
@RequestMapping("/worker")
public class WorkerController {
    @Resource
    private WorkerService workersService;

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "worker",value = "员工对象"),
            @ApiImplicitParam(name = "rememberMe",value = "是否记住"),
    })
    @GetMapping("login")
    public JsonResult login(Worker worker, boolean rememberMe) throws Exception{
        System.out.println(worker.getTel());
        //String password=MD5Utils.MD5EncodeUtf8(user.getPassword());
        worker.setPassword(worker.getPassword());
        //将登陆操作委托给shiro来完成
        UsernamePasswordToken token = new UsernamePasswordToken(worker.getTel(), worker.getPassword(),rememberMe);
        //调用shiro的login方法
        Subject subject = SecurityUtils.getSubject();
        //在未登陆的情况下才进行登录
        if(!subject.isAuthenticated()&&!subject.isRemembered()) {
            subject.login(token);
        }
        return new JsonResult(200,"success",null,null);
    }
    //得到用户的3级按钮
    @ApiOperation("得到员工的3级按钮")
    @ApiImplicitParam(name = "pid",value = "父id")
    @GetMapping("selectButton")
    public JsonResult selectButton(Integer pid){
        Subject subject = SecurityUtils.getSubject();
//        System.out.println(subject.isAuthenticated());
        Worker worker = (Worker)subject.getPrincipal();
        return new JsonResult(200, "success",workersService.selectButton(worker.getId(),pid),null );
    }
    //得到用户的1、2级菜单
    @ApiOperation("得到员工的1、2级菜单")
    @GetMapping("/selectMenu")
    public JsonResult selectMenu(){
        Subject subject = SecurityUtils.getSubject();
        Worker worker = (Worker)subject.getPrincipal();
        return new JsonResult(200, "success",workersService.selectMenu(worker),null );
    }
}

