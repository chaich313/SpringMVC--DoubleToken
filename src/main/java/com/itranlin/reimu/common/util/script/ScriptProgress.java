package com.itranlin.reimu.common.util.script;

/**
 * @author itranlin
 * @since 2022/6/7 22:46
 */
@FunctionalInterface
public interface ScriptProgress {

    /**
     * 计算进度
     *
     * @param out 输出行
     * @return 进度
     */
    Object progress(String out);
}
