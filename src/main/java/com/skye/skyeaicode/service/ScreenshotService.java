package com.skye.skyeaicode.service;

/**
 * 截图服务
 */
public interface ScreenshotService {

    /**
     * 通用的截图服务
     * @param webUrl
     * @return
     */
    String generateAndUploadScreenshot(String webUrl);

}
