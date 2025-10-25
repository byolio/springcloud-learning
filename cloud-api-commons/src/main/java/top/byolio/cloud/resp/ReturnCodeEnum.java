package top.byolio.cloud.resp;

import java.util.Arrays;

/**
 * ClassName: ReturnCodeEnum
 * Description:
 *
 * @Author Byolio
 * @Create 2025/10/23 16:28
 * @Version 1.0
 */
public enum ReturnCodeEnum {
    RC999("999", "操作xxx失败"),
    RC200("200", "操作成功"),
    RC201("201", "开启降级保护, 稍后再试"),
    RC202("202", "热点数据限流, 稍后再试"),
    RC404("404", "页面找不到"),
    RC500("500", "服务器错误");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 传统版遍历
    public static ReturnCodeEnum getReturnCodeEnum1(String code) {
        for (ReturnCodeEnum element : ReturnCodeEnum.values()) {
            if(element.getCode().equals(code)){
                return element;
            }
        }
        return null;
    }

    // stream流式计算版
    public static ReturnCodeEnum getReturnCodeEnum2(String code) {
        return Arrays.stream(ReturnCodeEnum.values()).filter(
                x -> x.getCode().equalsIgnoreCase(code))
                .findFirst().orElse(null);
    }
}
