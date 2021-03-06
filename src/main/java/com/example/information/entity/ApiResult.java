package com.example.information.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResult<T> implements java.io.Serializable{
    /**
     * 错误码，表示一种错误类型
     * 请求成功，状态码为200
     */

    private int code;

    /**
     * 对错误代码的详细解释
     */
    private String message;

    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private T data;

    public static ApiResult succ(Object data) {
        ApiResult m = new ApiResult();
        m.setCode(200);
        m.setData(data);
        m.setMessage("操作成功");
        return m;
    }
    public static ApiResult fail(String mess) {

        ApiResult m = new ApiResult();
        m.setCode(500);
        m.setData(null);
        m.setMessage(mess);
        return m;
    }
    public static ApiResult fail(String mess, Object data) {
        ApiResult m = new ApiResult();
        m.setCode(500);
        m.setData(data);
        m.setMessage(mess);
        return m;
    }
}
