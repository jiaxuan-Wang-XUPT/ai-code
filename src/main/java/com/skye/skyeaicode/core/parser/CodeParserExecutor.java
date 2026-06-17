package com.skye.skyeaicode.core.parser;

import com.skye.skyeaicode.exception.BusinessException;
import com.skye.skyeaicode.exception.ErrorCode;
import com.skye.skyeaicode.model.enums.CodeGenTypeEnum;

/**
 * 根据代码生成类型执行相应的解析逻辑
 */
public class CodeParserExecutor {

    private static final HtmlCodeParser htmlCodeParser = new HtmlCodeParser();

    private static final MultiFileCodeParser multiFileCodeParser = new MultiFileCodeParser();

    public static Object executeParser(String codeContent, CodeGenTypeEnum codeGenType) {

        return switch (codeGenType){
            case HTML -> htmlCodeParser.parseCode(codeContent);
            case MULTI_FILE -> multiFileCodeParser.parseCode(codeContent);
            default -> throw new BusinessException(ErrorCode.SYSTEM_ERROR,"不支持代码生成类型" + codeGenType);
        };

    }



}
