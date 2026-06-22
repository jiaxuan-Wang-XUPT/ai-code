package com.skye.skyeaicode.config;

import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Data
@Configuration
@ConfigurationProperties(prefix = "langchain4j.open-ai.chat-model")
public class ResoningStreamingChatModelConfig {

    private String baseUrl;

    private String apiKey;

    /**
     * 获取推理流式聊天模型
     * @return
     */
    @Primary
    @Bean
    public StreamingChatModel reasoningStreamingChatModel() {
        //为了测试
        final String modelName = "deepseek-chat";
        final int maxTokens = 8192;

//        生成环境使用
//        final String modelName = "deepseek-reasoner";
//        final int maxTokens = 32768;

        return OpenAiStreamingChatModel.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .modelName(modelName)
                .maxTokens(maxTokens)
                .logRequests(true)
                .logResponses(true)
                .build();

    }



}
