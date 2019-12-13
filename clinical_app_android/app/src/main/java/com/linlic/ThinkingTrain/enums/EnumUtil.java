package com.linlic.ThinkingTrain.enums;

/**
 * <pre>
 *     author : eric
 *     version: 1.0
 * </pre>
 */

public class EnumUtil {

    /*
    * 根据code返回一个Enum实例
    */
    public static <T extends EnumHelper> T findByCode(int code, Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();

        for (T t : enumConstants) {
            if (t.getCode() == code)
                return t;
        }
        return null;
    }


    /*
     * 根据code返回一个msg字符串
     */
    public static <T extends EnumHelper> String findMsgByCode(int code, Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();

        for (T t : enumConstants) {
            if (t.getCode() == code)
                return t.getMsg();
        }
        return "";
    }
}






