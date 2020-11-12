package com.yuekehoutai.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuekehoutai.Dto.InformationDto;
import com.yuekehoutai.domain.Activity;
import com.yuekehoutai.domain.Information;
import com.yuekehoutai.domain.param.ActInsertParam;
import com.yuekehoutai.exception.ProjectException;
import com.yuekehoutai.param.InformationParam;
import com.yuekehoutai.service.ViewService;
import com.yuekehoutai.util.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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
@Controller
@RequestMapping("/view")
public class ViewController {

}

