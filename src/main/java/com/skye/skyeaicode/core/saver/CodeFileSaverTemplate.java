package com.skye.skyeaicode.core.saver;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.skye.skyeaicode.constant.AppConstant;
import com.skye.skyeaicode.exception.BusinessException;
import com.skye.skyeaicode.exception.ErrorCode;
import com.skye.skyeaicode.model.enums.CodeGenTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * 抽象代码文件保存器-模板方法模式
 */
@Slf4j
public abstract class CodeFileSaverTemplate<T> {

    // 文件保存根目录
    protected static final String FILE_SAVE_ROOT_DIR = AppConstant.CODE_OUTPUT_ROOT_DIR;


    public final File saveCode(T result,Long appId){
        //1。验证输入
        validateInput(result);
        //2.构建唯一目录
        String baseDirPath = buildUniqueDir(appId);
        //3.保存文件（具体实现由子类提供）
        saveFiles(result, baseDirPath);
        //4.返回文件目录
        return new File(baseDirPath);
    }


    /**
     * 验证输入
     */
    protected void validateInput(T result) {
        if (result == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "输入不能为空");
        }
    }

    /**
     * 构建唯一目录路径
     */
//    protected final String buildUniqueDir(Long appId){
//        if (appId == null) {
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "应用Id不能为空");
//        }
//        String codeType = getCodeType().getValue();
//        String uniqueDirName = StrUtil.format("{}_{}", codeType, IdUtil.getSnowflakeNextIdStr());
//        String dirPath = FILE_SAVE_ROOT_DIR + "/" + uniqueDirName;
//        FileUtil.mkdir(dirPath);
//        return dirPath;
//    }
    // CodeFileSaverTemplate.java
    protected final String buildUniqueDir(Long appId){
        if (appId == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "应用Id不能为空");
        }
        String codeType = getCodeType().getValue();
        String dirName = StrUtil.format("{}_{}", codeType, appId);   // 使用 appId
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + dirName;

        // 如果目录已存在，先删除旧内容（保证完全覆盖）
        File dir = new File(dirPath);
        if (dir.exists()) {
            FileUtil.del(dir);
        }
        FileUtil.mkdir(dirPath);
        return dirPath;
    }

    /**
     * 写入单个文件的工具方法
     */
//    protected final void writeToFile(String dirPath, String fileName, String content) {
//        if (StrUtil.isBlank(content)) {
//            String filePath = dirPath + File.separator + fileName;
//            FileUtil.writeString(content,filePath, StandardCharsets.UTF_8);
//        }
//    }
    protected final void writeToFile(String dirPath, String fileName, String content) {
        if (StrUtil.isNotBlank(content)) {   // 非空才写入
            String filePath = dirPath + File.separator + fileName;
            FileUtil.writeString(content, filePath, StandardCharsets.UTF_8);
        } else {
            log.warn("跳过写入空文件: {}", fileName);
        }
    }


    /**
     * 获取代码类型
     */
    protected abstract CodeGenTypeEnum getCodeType();

    /**
     * 保存文件的具体实现
     */
    protected abstract void saveFiles(T result, String baseDirPath);



}
