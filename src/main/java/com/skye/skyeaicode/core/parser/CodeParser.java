package com.skye.skyeaicode.core.parser;

/**
 * 代码解析器策略接口
 *
 */
public interface CodeParser<T> {

    /**
     * 解析代码内容
     * <T>是泛型，因为不确定解析器返回值是什么，返回值不固定，要用泛型交给子类实现
     * @param codeContent 原始代码内容
     * @return 解析后的结果对象
     */
    T parseCode(String codeContent);
}
