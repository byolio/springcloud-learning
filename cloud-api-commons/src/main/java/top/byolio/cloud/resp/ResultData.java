package top.byolio.cloud.resp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ClassName: ResultData
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/23 17:07
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;   // 接口调用时间

    public ResultData(String code, String message, T data) {
        timestamp = System.currentTimeMillis();  // 添加接口调用时间
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultData(ReturnCodeEnum code, T data) {
        this(code.getCode(), code.getMessage(), data);
    }

    public static<T> ResultData<T> success(T data) {
        return new ResultData<>(ReturnCodeEnum.RC200, data);
    }

    public static<T> ResultData<T> fail(ReturnCodeEnum code) {
        return new ResultData<>(code, null);
    }

    public static<T> ResultData<T> fail(ReturnCodeEnum code, String message) {
        return new ResultData<>(code.getCode(), message, null);
    }

}
