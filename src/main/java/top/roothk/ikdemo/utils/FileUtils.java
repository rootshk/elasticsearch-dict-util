package top.roothk.ikdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * 合同工具类
 */
@Slf4j
@Component
public class FileUtils {

    //linux保存路径
    @Value("${ik.file-path.linuxFilePath}")
    private String linuxFilePath;

    //windows保存路径
    @Value("${ik.file-path.windowsFilePath}")
    private String windowsFilePath;

    private Path getContractDir() {
        Path path;
        //查看系统版本,通过不同的系统版本设置不同的保存路径
        if (System.getProperty("os.name").contains("Windows")) {
            path = Paths.get(windowsFilePath);
        } else {
            path = Paths.get(linuxFilePath);
        }
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            } else if (!Files.isDirectory(path)) {
                log.error("{}是一个普通文件，无法创建文件夹以存放json文件。", path);
                throw new IllegalArgumentException("文件夹路径已存在,但是它是一个普通文件而非文件夹");
            }
        } catch (Exception e) {
            log.error("获取合同文件夹异常", e);
        }
        return path;
    }

    /**
     * 通过名字查找JSON
     * @param name
     * @return
     */
    public JSONArray findByName(String name) {
        try {
            Path path = getContractDir().resolve(name + ".json");
            if (Files.exists(path)) {
                String fileContent = Files.lines(path, Charset.forName("UTF-8")).collect(Collectors.joining());
                return JSONArray.parseArray(fileContent);
            }
        } catch (Exception e) {
            log.error("读取文件异常", e);
        }
        return null;
    }

    /**
     * 是否存在指定名称的文件
     *
     * @param name 文件名称
     * @return 是否存在
     */
    public boolean existsByName(String name) {
        try {
            Path path = getContractDir().resolve(name + ".json");
            return Files.exists(path);
        } catch (Exception e) {
            log.error("检查文件[{}]异常", name, e);
        }
        return false;
    }

    /**
     * 保存
     * @param o
     * @param name
     */
    public void saveJSON(Object o, String name) {
        try {
            Path dir = getContractDir();
            final BufferedWriter writer = Files.newBufferedWriter(dir.resolve(name + ".json"),
                    Charset.forName("UTF-8"));
            JSON.writeJSONString(writer, o);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            log.error("写入内容异常", e);
        }
    }

}
