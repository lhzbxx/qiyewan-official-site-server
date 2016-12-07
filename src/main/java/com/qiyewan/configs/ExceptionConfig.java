package com.qiyewan.configs;

import com.qiyewan.dto.ResultDto;
import com.qiyewan.enums.ErrorType;
import com.qiyewan.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.PersistenceException;

/**
 * Created by lhzbxx on 2016/10/28.
 *
 * 异常结果的处理
 */
@ControllerAdvice
public class ExceptionConfig {
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public ResultDto processException(Exception e) throws Exception {
//        return new ResultDto(ErrorType.UnknownError, "未知的错误。");
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultDto processValidationError(MethodArgumentNotValidException e) throws Exception {
        return new ResultDto(ErrorType.InvalidParamError, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultDto processMethodArgumentTypeError(MethodArgumentTypeMismatchException e) throws Exception {
        return new ResultDto(ErrorType.InvalidMethodError, e.getMessage());
    }

    @ExceptionHandler(ExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultDto processDuplicatedError(ExistedException e) throws Exception {
        return new ResultDto(ErrorType.ExistedError, e.getMessage());
    }

    @ExceptionHandler(value = NoAuthException.class)
    @ResponseStatus(value = HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ResponseBody
    public ResultDto processAuthError(NoAuthException e) throws Exception {
        return new ResultDto(ErrorType.NoAuthError, e.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResultDto processNotFoundError(NotFoundException e) throws Exception {
        return new ResultDto(ErrorType.NotFoundError, e.getMessage());
    }

    @ExceptionHandler(value = PersistenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultDto processPersistenceError(PersistenceException e) throws Exception {
        return new ResultDto(ErrorType.DatabaseError, e.getMessage());
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResultDto processIllegalActionError(InvalidRequestException e) throws Exception {
        return new ResultDto(ErrorType.InvalidRequestError, e.getMessage());
    }

    @ExceptionHandler(value = InvalidParamException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ResultDto processInvalidParamError(InvalidParamException e) throws Exception {
        return new ResultDto(ErrorType.InvalidParamError, e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultDto processMissingParamError(MissingServletRequestParameterException e) throws Exception {
        return new ResultDto(ErrorType.InvalidParamError, "缺少`" + e.getParameterName() + "`参数。");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultDto processNotSupportMethodError(HttpRequestMethodNotSupportedException e) {
        return new ResultDto(ErrorType.InvalidMethodError, "请求方法不正确。");
    }

    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultDto processUnsupportedMediaError(HttpMediaTypeException e) {
        return new ResultDto(ErrorType.UnsupportedMedia, "请使用`JSON`格式传输数据。");
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ResultDto processHttpRequestBodyError(HttpMessageNotReadableException e) {
        return new ResultDto(ErrorType.InvalidParamError, e.getMessage());
    }
}
