package com.qiyewan.exceptions;

import com.qiyewan.dto.ErrorDto;
import com.qiyewan.enums.ErrorType;
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
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto<String> processValidationError
            (MethodArgumentNotValidException e) throws Exception {
        return new ErrorDto<>(ErrorType.InvalidParamError, e.getMessage());
    }

    @ExceptionHandler(DuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto<String> processDuplicatedError(DuplicatedException e) throws Exception {
        return new ErrorDto<>(ErrorType.DuplicatedError, e.getMessage());
    }

    @ExceptionHandler(value = NoAuthException.class)
    @ResponseStatus(value = HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    @ResponseBody
    public ErrorDto<String> processAuthError(NoAuthException e) throws Exception {
        return new ErrorDto<>(ErrorType.NoAuthError, e.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDto<String> processNotFoundError(NotFoundException e) throws Exception {
        return new ErrorDto<>(ErrorType.NotFoundError, e.getMessage());
    }

    @ExceptionHandler(value = PersistenceException.class)
    @ResponseBody
    public ErrorDto<String> processPersistenceError(PersistenceException e) throws Exception {
        return new ErrorDto<>(ErrorType.DatabaseError, e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ErrorDto<String> processMethodArgumentTypeError(MethodArgumentTypeMismatchException e) throws Exception {
        return new ErrorDto<>(ErrorType.InvalidMethodError, e.getMessage());
    }

    @ExceptionHandler(value = IllegalActionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto<String> processIllegalActionError(IllegalActionException e) throws Exception {
        return new ErrorDto<>(ErrorType.IllegalActionError, e.getMessage());
    }

    @ExceptionHandler(value = InvalidParamException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorDto processInvalidParamError(InvalidParamException e) throws Exception {
        return new ErrorDto<>(ErrorType.InvalidParamError, e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto processMissingParamError(MissingServletRequestParameterException e) throws Exception {
        return new ErrorDto<>(ErrorType.LackParamError, e.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto processNotSupportMethodError(HttpRequestMethodNotSupportedException e) {
        return new ErrorDto(ErrorType.InvalidMethodError);
    }

    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto processUnsupportedMediaError(HttpMediaTypeException e) {
        return new ErrorDto(ErrorType.UnsupportedMedia);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorDto processHttpRequestBodyError(HttpMessageNotReadableException e) {
        return new ErrorDto<>(ErrorType.InvalidParamError, e.getMessage());
    }

}
