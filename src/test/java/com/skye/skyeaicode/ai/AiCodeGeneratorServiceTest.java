package com.skye.skyeaicode.ai;

import com.skye.skyeaicode.ai.model.HtmlCodeResult;
import com.skye.skyeaicode.ai.model.MultiFileCodeResult;
import com.skye.skyeaicode.core.AiCodeGeneratorFacade;
import com.skye.skyeaicode.model.enums.CodeGenTypeEnum;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class AiCodeGeneratorServiceTest {

    // 移除直接注入 AiCodeGeneratorService
     @Resource
     private AiCodeGeneratorService aiCodeGeneratorService;

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Resource
    private AiCodeGeneratorServiceFactory aiCodeGeneratorServiceFactory;  // 注入工厂

    @Test
    void generateHtmlCode() {
        // 通过工厂获取服务实例（需传入 appId 和类型）
        AiCodeGeneratorService service = aiCodeGeneratorServiceFactory.getAiCodeGeneratorService(1L, CodeGenTypeEnum.HTML);
        HtmlCodeResult result = service.generateHtmlCode("做个程序员skye的工作记录小工具");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {
        AiCodeGeneratorService service = aiCodeGeneratorServiceFactory.getAiCodeGeneratorService(1L, CodeGenTypeEnum.MULTI_FILE);
        MultiFileCodeResult multiFileCode = service.generateMultiFileCode("做个程序员skye的留言板");
        Assertions.assertNotNull(multiFileCode);
    }

}