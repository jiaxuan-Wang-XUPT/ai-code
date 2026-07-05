package com.skye.skyeaicode.ai;

import com.skye.skyeaicode.model.enums.CodeGenTypeEnum;
import dev.langchain4j.service.SystemMessage;

/**
 * AI代码生成类型只能路由服务（由ai决策提高给用户哪种生成代码类型HTML VUE ...）
 * 使用结构化输出直接返回枚举类
 */
public interface AiCodeGenTypeRoutingService {

    @SystemMessage(fromResource = "prompt/codegen-routing-system-prompt.txt")
    CodeGenTypeEnum routeCodeGenType(String userPrompt);

}
