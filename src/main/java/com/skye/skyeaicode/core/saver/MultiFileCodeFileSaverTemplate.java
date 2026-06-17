package com.skye.skyeaicode.core.saver;

import cn.hutool.core.util.StrUtil;
import com.skye.skyeaicode.ai.model.MultiFileCodeResult;
import com.skye.skyeaicode.exception.BusinessException;
import com.skye.skyeaicode.exception.ErrorCode;
import com.skye.skyeaicode.model.enums.CodeGenTypeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 多文件代码保存器
 */
@Slf4j
public class MultiFileCodeFileSaverTemplate extends CodeFileSaverTemplate<MultiFileCodeResult> {

    @Override
    public CodeGenTypeEnum getCodeType() {
        return CodeGenTypeEnum.MULTI_FILE;
    }

    @Override
    protected void saveFiles(MultiFileCodeResult result, String baseDirPath) {

        //验证（可删）
        log.info("【MultiFileSaver】开始保存到目录: {}", baseDirPath);
        log.info("【MultiFileSaver】待保存内容长度 - HTML: {}, CSS: {}, JS: {}",
                result.getHtmlCode() == null ? 0 : result.getHtmlCode().length(),
                result.getCssCode() == null ? 0 : result.getCssCode().length(),
                result.getJsCode() == null ? 0 : result.getJsCode().length());

        // 保存 HTML 文件
        writeToFile(baseDirPath, "index.html", result.getHtmlCode());
        // 保存 CSS 文件
        writeToFile(baseDirPath, "style.css", result.getCssCode());
        // 保存 JavaScript 文件
        writeToFile(baseDirPath, "script.js", result.getJsCode());
    }

    @Override
    protected void validateInput(MultiFileCodeResult result) {
        super.validateInput(result);
        //验证（可删）
        log.info("【MultiFileSaver】validateInput - HTML是否为空: {}", StrUtil.isBlank(result.getHtmlCode()));

        // 至少要有 HTML 代码，CSS 和 JS 可以为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "HTML代码内容不能为空");
        }
    }
}
