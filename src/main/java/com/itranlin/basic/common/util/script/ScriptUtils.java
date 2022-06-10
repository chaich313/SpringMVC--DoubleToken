package com.itranlin.basic.common.util.script;

import com.itranlin.basic.admin.service.WebSocketService;
import com.itranlin.basic.common.bean.StatusEnum;
import com.itranlin.basic.common.exception.RequestException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Resource;

/**
 * @author itranlin
 * @since 2022/6/1 20:50
 */
@Slf4j
@Component
public class ScriptUtils {

    private static final Pattern PROGRESS_PATTERN = Pattern.compile("progress-\\d");
    // 正则表达式  创建一个pattern实例
    // Pattern构造器是私有的，不能通过new创建Pattern对象，可以通过Pattern调用静态方法compile返回Pattern实例。

    private static final String WINDOWS_STR = "windows";
    @Resource
    private WebSocketService webSocketService;

    @Async
    public void execHello(String... params) {
        execShellScript("/topic/message", "hello-script.sh", lineText -> {
            try {
//                log.debug(lineText);
                if (PROGRESS_PATTERN.matcher(lineText).matches()) {
                    return (Integer.parseInt(lineText.split("-")[1]) + 1) * 10;
                } else {
                    return null;
                }
                // Intteger能在int和string之间转化，将字符串s转换为十进制的数字
                // parseInt将字符串s转换为十进制的数字
                // 拆分字符串and？？
            } catch (Exception e) {
                // 异常 && 例外  是一个在程序执行期间发生的事件,它中断正在执行程序的正常指令流
                return null;
            }
        }, params);
    }

    public void execShellScript(String noticePath, String scriptFile, ScriptProgress progress, String... params) {
        File shellFile;
        // 该类主要用于文件和目录的创建、文件的查找和文件的删除等
        try {
            shellFile = ResourceUtils.getFile("classpath:script/" + scriptFile);
            // ResourceUtils 工具类,它支持“classpath:”和“file:”的地址前缀,它能够从指定的地址加载文件资源
        } catch (FileNotFoundException e) {
            // 文件找不到的异常的处理方式
            throw new RequestException(StatusEnum.FAIL, "脚本文件不存在", e);
            // StatusEnum.FAIL 报错信息的对应

        }
        List<String> cmdGroup = new ArrayList<>();
        cmdGroup.add(getShellCmd());
        cmdGroup.add(scriptFile);
        cmdGroup.addAll(Arrays.asList(params));
        ProcessBuilder pb = new ProcessBuilder(cmdGroup);
        // 创建一个pb操作系统进程 — — 用于放脚本
        pb.directory(shellFile.getParentFile());
        // directory用于生成工作目录
        try {
            Process p = pb.start();
            // 创建一个本地进程
            InputStream inputStream = p.getInputStream();
            // 输出流          getInputStream获取流程和子流程的输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream), 4096);
            // 从输入流中读取文本,缓冲各个字符,从而提供字符,数组和行的高效读取。
            // 是从字节流到字符流的桥梁, 它读取字节并使用指定的java.nio.charset.Charset将其解码为字符。？
            while (p.isAlive()) {
                // 当本地进程处于活动状态:线程已经启动但尚未中止
                String out = reader.readLine();
                // 刚读取的文本进行读入
                Object progressText = progress.progress(out);
                if (null != progressText) {
                    webSocketService.send(noticePath, progressText);
                }
            }
        } catch (IOException e) {
            // 异常
            log.error("error", e);
        }
        log.debug("sh exec finished");
    }

    public String getShellCmd() {
        String os = System.getProperty("os.name");
        // System.getProperty方法用来读取JVM中的系统属性
        if (os.toLowerCase().startsWith(WINDOWS_STR)) {
            // to方法用将字符串转化为小写，start方法用于检测字符串是否以windows开头
            return "bash";
        } else {
            return "sh";
        }
    }
}
