package com.wds.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-20 22:52
 */
@Data
public class JsonResult implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public JsonResult() {

    }

    /**
     *  成功状态码默认1
     */
    public static JsonResult ok(Object data) {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg("操作成功");
        result.setData(data);

        return result;
    }

    /**
     *  成功
     * @param code 自定义成功状态码
     */
    public static JsonResult ok(Integer code ,Object data) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(null);
        result.setData(data);

        return result;
    }

    /**
     *  失败默认状态码 0
     */
    public static JsonResult error(String message) {
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setMsg(message);
        result.setData(null);

        return result;
    }
    /**
     * 失败 自定也状态码
     * @param code 失败状态码
     * @param message 失败信息
     */
    public static JsonResult error(Integer code, String message) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(message);
        result.setData(null);

        return result;
    }

    public static JsonResult ok() {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg("操作成功");
        result.setData(null);

        return result;
    }
}
