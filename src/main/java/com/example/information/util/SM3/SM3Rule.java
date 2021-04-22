package com.example.information.util.SM3;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName SM3Rule
 * @Author xiaomingds
 * @Date 2021/4/20 19:29
 **/
public enum SM3Rule {
    /**
     * 转码规则 与上面讲的对应
     */
    ZERO("0", "aa"),
    ONE("1", "bb"),
    TWO("2", "cc"),
    THREE("3", "dd"),
    FOUR("4", "ee"),
    FIVE("5", "ff"),
    SIX("6", "gg"),
    SEVEN("7", "hh"),
    EIGHT("8", "ii"),
    NINE("9", "jj"),
    L("l", "ll"),
    M("m", "mm"),
    ;

    @Getter
    private String code;
    @Getter
    private String msg;

    SM3Rule(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String valueOfCode(String code) {
        for (SM3Rule sm3Rule : values()) {
            if (StringUtils.equals(code, sm3Rule.code)) {
                return sm3Rule.msg;
            }
        }
        throw new IllegalStateException("数字转换异常");
    }

}
