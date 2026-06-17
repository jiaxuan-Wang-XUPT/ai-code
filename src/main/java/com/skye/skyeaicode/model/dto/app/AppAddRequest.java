package com.skye.skyeaicode.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * 整体流程如下：
 *  * 用户在主页输入提示词后，系统会创建一个应用记录，然后跳转到对话页面与 A1交互生成网站。
 *  * 生成完成后，用户可以预览效果，满意后进行部署，让网站真正对外提供服务。
 *
 * 这一步是创建应用，应用创建请求
 *
 */
@Data
public class AppAddRequest implements Serializable {

    /**
     * 应用初始化的 prompt
     */
    private String initPrompt;

    private static final long serialVersionUID = 1L;
}
