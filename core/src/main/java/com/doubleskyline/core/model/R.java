package com.doubleskyline.core.model;

import com.doubleskyline.core.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author ZZY
 * @date 2019年6月5日
 */
@Data
@ApiModel("执行结果")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int OK = 0;
    /**
     * 状态码
     */
    @ApiModelProperty("状态码")
	private int status;
	/**
	 * 执行状态
	 */
    @ApiModelProperty("反馈信息")
	private String message;

    /**
     * 查询结果
     */
    @ApiModelProperty("数据")
	private T result;

	public R(int status, String message) {
		this.status = status;
		this.message = message;
	}

	@ApiOperation("500 未知异常，请联系管理员")
	public static R error() {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
	}

	@ApiOperation("500 系统错误!")
	public static R error(String msg) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}

	public static R error(int code, String msg) {
		return new R(code, msg);
	}

	public static R ok(String msg) {
		return new R(OK, msg);
	}
	
	public static R ok() {
		return new R(0, "success");
	}

    @ApiOperation("放入数据")
	public R result(T data){
		this.result = data;
		return this;
	}

	@ApiOperation("放入信息")
	public R message(String msg){
		this.message = msg;
		return this;
	}

	@JsonIgnore
	public T getResultWithException(){
		if(this.status != OK){
			throw new BusinessException(message, status);
		}
		return result;
	}
}
