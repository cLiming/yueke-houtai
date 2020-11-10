package com.yuekehoutai.exception;

import javax.validation.ValidationException;

import com.yuekehoutai.util.JsonResult;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class GlobalExceptionHandler {
	//定义不同的方法处理不同类型的异常
	@ExceptionHandler({BindException.class,ValidationException.class})
	public ResponseEntity<JsonResult> handlerBindException(Exception e) {
		e.printStackTrace();
		JsonResult jr = new JsonResult(500, "参数异常", null, null);
		return new ResponseEntity<JsonResult>(jr, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({IncorrectCredentialsException.class})
	public ResponseEntity<JsonResult> IncorrectCredentialsException(Exception e) {
		e.printStackTrace();
		JsonResult jr = new JsonResult(500, "密码错误", null, null);
		return new ResponseEntity<JsonResult>(jr,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({UnknownAccountException.class})
	public JsonResult handlerUnkownAccountException(Exception e) {
		e.printStackTrace();
		return new JsonResult(500,"用户名不存在",null,null);
	}
	@ExceptionHandler({AuthorizationException.class})
	public ResponseEntity<JsonResult> handlerAuthorizationException(Exception e) {
		e.printStackTrace();
		JsonResult jr = new JsonResult(500, "权限不足", null, null);

		return new ResponseEntity<JsonResult>(jr,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({ProjectException.class})
	public ResponseEntity<JsonResult> projectExceptionError(ProjectException e) {
		e.printStackTrace();
		JsonResult jr = new JsonResult(e.getCode(), e.getMessage(), null, null);
		return new ResponseEntity<JsonResult>(jr,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({Exception.class})
	public ResponseEntity<JsonResult> handlerException(Exception e) {
		e.printStackTrace();
		JsonResult jr = new JsonResult(500, "服务器异常", null, null);
		return new ResponseEntity<JsonResult>(jr,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
