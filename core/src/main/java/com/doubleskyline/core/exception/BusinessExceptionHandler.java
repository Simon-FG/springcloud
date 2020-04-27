package com.doubleskyline.core.exception;

import com.doubleskyline.core.model.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author zzy
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class BusinessExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理404异常
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		logger.error(e.getMessage(), e);
		return R.error(HttpStatus.NOT_FOUND.value(), "路径不存在，请检查路径是否正确");
	}

	/**
	 * 处理请求方式不支持异常
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public R handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		logger.error(e.getMessage(), e);
		return R.error(HttpStatus.METHOD_NOT_ALLOWED.value(), "请求方式不正确：" + e.getMethod());
	}

	/**
	 * 处理请求超时异常
	 */
	@ExceptionHandler(AsyncRequestTimeoutException.class)
	public R handlerAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
		logger.error(e.getMessage(), e);
		return R.error(HttpStatus.REQUEST_TIMEOUT.value(), "请求超时");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	/**
	 * 处理上传大小异常
	 * HttpStatus.SC_INTERNAL_SERVER_ERROR 500 文件大小超出最大范围
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public R handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
		logger.error(e.getMessage(), e);
		return R.error("文件大小超出最大范围!");
	}

	/**
	 * 缺少所需的请求主体
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public R handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		logger.error(e.getMessage(), e);
		return R.error("无法解析请求参数!");
	}

	/**
	 * 参数错误或者执行结果断言失败
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public R handleIllegalArgumentException(IllegalArgumentException e){
		logger.error(e.getMessage(), e);
		return R.error(e.getMessage());
	}

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BusinessException.class)
	public R handleBusinessException(BusinessException e){
		logger.error(e.getMessage(), e);
		return R.error(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
}
