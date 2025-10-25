package top.byolio.cloud.exp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.byolio.cloud.resp.ResultData;
import top.byolio.cloud.resp.ReturnCodeEnum;

/**
 * ClassName: GlobalExceptionHandler
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/24 7:47
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exceptionHandler(Exception e) {
        System.out.println("come in global exceptionHandler");
        System.out.println(e.getMessage());
        return ResultData.fail(ReturnCodeEnum.RC500, e.getMessage());
    }
}
