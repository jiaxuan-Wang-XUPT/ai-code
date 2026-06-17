package com.skye.skyeaicode.ai;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.skye.skyeaicode.service.ChatHistoryService;
import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * 属于langchain4j的AIservice功能。AiServices.create动态代理生成；
 * 这个工厂负责生成 AiCodeGeneratorService 的“假实现”（其实是动态代理）。
 * <p>
 * 整体流程大概是这样的：
 * 1. AiServices.create(接口.class, chatModel)根据我们定义的接口，在内存里自动造出一个实现类。
 * 2. 当我们调用接口里的方法（比如 generateHtmlCode）时，这个代理会：
 *    - 看到方法上的 @SystemMessage 注解，去 classpath 下读对应的提示词文件。
 *    - 把我们传进来的用户消息和系统提示拼到一起。
 * 3. 拼好之后，交给注入的 ChatModel（比如 OpenAI 的模型）去真正请求 AI。
 * 4. AI 返回的结果直接作为字符串给到调用方。
 * <p>
 * 简单说：我们只管定义接口、写提示词、传用户消息，剩下的拼接参数、发请求、拿结果都由 LangChain4j 自动干了。
 *
 * streamingChatModel是流式模型，用于SSE流式输出
 */

@Configuration
@Slf4j
public class AiCodeGeneratorServiceFactory {

    @Resource
    private ChatModel chatModel;

    @Resource
    private StreamingChatModel streamingChatModel;

    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;

    @Resource
    private ChatHistoryService chatHistoryService;

    /**
     * AI 服务实例缓存
     * 缓存策略：
     * - 最大缓存 1000 个实例
     * - 写入后 30 分钟过期
     * - 访问后 10 分钟过期
     */
    private final Cache<Long, AiCodeGeneratorService> serviceCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(Duration.ofMinutes(30))
            .expireAfterAccess(Duration.ofMinutes(10))
            .removalListener((key, value, cause) -> {
                log.debug("AI 服务实例被移除，appId: {}, 原因: {}", key, cause);
            })
            .build();

    /**
     * 根据 appId 获取服务（带缓存）
     */
    public AiCodeGeneratorService getAiCodeGeneratorService(long appId) {
        return serviceCache.get(appId, this::createAiCodeGeneratorService);
    }

    /**
     * 创建新的 AI 服务实例
     */
    private AiCodeGeneratorService createAiCodeGeneratorService(long appId) {
        log.info("为 appId: {} 创建新的 AI 服务实例", appId);
        // 根据 appId 构建独立的对话记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory
                .builder()
                .id(appId)
                .chatMemoryStore(redisChatMemoryStore)
                .maxMessages(20)
                .build();
        //从数据库中加在历史对话到记忆中
        chatHistoryService.loadChatHistoryToMemory(appId, chatMemory,20 );
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();
    }


}

