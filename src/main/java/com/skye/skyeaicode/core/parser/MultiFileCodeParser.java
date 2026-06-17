package com.skye.skyeaicode.core.parser;

import com.skye.skyeaicode.ai.model.MultiFileCodeResult;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 多文件代码解析器（HTML + CSS + JS
 */
@Slf4j
public class MultiFileCodeParser implements CodeParser<MultiFileCodeResult> {

    private static final Pattern HTML_CODE_PATTERN = Pattern.compile("```html\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);
    private static final Pattern CSS_CODE_PATTERN = Pattern.compile("```css\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);
    private static final Pattern JS_CODE_PATTERN = Pattern.compile("```(?:js|javascript)\\s*\\n([\\s\\S]*?)```", Pattern.CASE_INSENSITIVE);

    @Override
    public MultiFileCodeResult parseCode(String codeContent) {
        //验证（可删）
        log.info("【MultiFileCodeParser】开始解析，原始内容长度: {}", codeContent == null ? 0 : codeContent.length());
        if (codeContent != null && codeContent.length() > 0) {
            // 打印前200字符帮助诊断格式
            String preview = codeContent.length() > 200 ? codeContent.substring(0, 200) : codeContent;
            log.debug("【MultiFileCodeParser】内容预览: {}", preview);
        }

        MultiFileCodeResult result = new MultiFileCodeResult();
        // 提取各类代码
        String htmlCode = extractCodeByPattern(codeContent, HTML_CODE_PATTERN);
        String cssCode = extractCodeByPattern(codeContent, CSS_CODE_PATTERN);
        String jsCode = extractCodeByPattern(codeContent, JS_CODE_PATTERN);

        //验证（可删）
        log.info("【MultiFileCodeParser】提取结果 - HTML长度: {}, CSS长度: {}, JS长度: {}",
                htmlCode == null ? 0 : htmlCode.length(),
                cssCode == null ? 0 : cssCode.length(),
                jsCode == null ? 0 : jsCode.length());

        // 设置HTML代码
        if (htmlCode != null && !htmlCode.trim().isEmpty()) {
            result.setHtmlCode(htmlCode.trim());
        }
        // 设置CSS代码
        if (cssCode != null && !cssCode.trim().isEmpty()) {
            result.setCssCode(cssCode.trim());
        }
        // 设置JS代码
        if (jsCode != null && !jsCode.trim().isEmpty()) {
            result.setJsCode(jsCode.trim());
        }

        //验证（可删）
        log.info("【MultiFileCodeParser】最终 result - HTML长度: {}, CSS长度: {}, JS长度: {}",
                result.getHtmlCode() == null ? 0 : result.getHtmlCode().length(),
                result.getCssCode() == null ? 0 : result.getCssCode().length(),
                result.getJsCode() == null ? 0 : result.getJsCode().length());

        return result;
    }

    /**
     * 根据正则模式提取代码
     *
     * @param content 原始内容
     * @param pattern 正则模式
     * @return 提取的代码
     */
    private String extractCodeByPattern(String content, Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
