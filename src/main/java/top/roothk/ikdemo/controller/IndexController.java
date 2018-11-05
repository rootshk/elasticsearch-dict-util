package top.roothk.ikdemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.roothk.ikdemo.bean.IkDict;
import top.roothk.ikdemo.service.FileService;

@Slf4j
@RestController
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private FileService fileService;

    @Value("${ik.file-name.dict}")
    private String dict;
    @Value("${ik.file-name.stopwords}")
    private String stopwords;
    /**
     * 获取所有的扩展词典
     * @return
     */
    @GetMapping("/dict")
    public JSONArray getDict() {
        return IkDict.DICT;
    }

    /**
     * 获取所有的扩展停止词字典
     * @return
     */
    @GetMapping("/stopwords")
    public JSONArray getStopWords() {
        return IkDict.STOPWORDS;
    }

    /**
     * 添加扩展词典
     * @return
     */
    @PostMapping("/dict")
    public String addDict(@RequestBody JSONObject data) {
        if (!data.containsKey("value"))
            return "参数错误";
        String value = data.getString("value");
        if (IkDict.DICT.contains(value)) {
            log.info(IkDict.DICT.toJSONString());
            return "词典已经有这个词";
        } else {
            IkDict.DICT.add(value);
            fileService.saveJSON(IkDict.DICT, dict);
            log.info("[添加] [  分词词典]: " + value);
            return "添加成功";
        }
    }

    /**
     * 添加停止词词典
     * @return
     */
    @PostMapping("/stopwords")
    public String addStopwords(@RequestBody JSONObject data) {
        if (!data.containsKey("value"))
            return "参数错误";
        String value = data.getString("value");
        if (IkDict.STOPWORDS.contains(value)) {
            return "词典已经有这个词";
        } else {
            IkDict.STOPWORDS.add(value);
            fileService.saveJSON(IkDict.STOPWORDS, stopwords);
            log.info("[添加] [停止词词典]: " + value);
            return "添加成功";
        }
    }

    /**
     * 更新扩展词典(没有则新增)
     * @param data
     * @return
     */
    @PutMapping("/dict")
    public String updateDict(@RequestBody JSONObject data) {
        if (!data.containsKey("newValue") || !data.containsKey("oldValue"))
            return "参数错误";
        String newValue = data.getString("newValue");
        String oldValue = data.getString("oldValue");
        IkDict.DICT.remove(oldValue);
        IkDict.DICT.add(newValue);
        fileService.saveJSON(IkDict.DICT, dict);
        log.info("[更新] [  分词词典]: " + oldValue + " 改为:" + newValue);
        return "更新成功";
    }

    /**
     * 更新停止词词典(没有则新增)
     * @param data
     * @return
     */
    @PutMapping("/stopwords")
    public String updateStopwords(@RequestBody JSONObject data) {
        if (!data.containsKey("newValue") || !data.containsKey("oldValue"))
            return "参数错误";
        String newValue = data.getString("newValue");
        String oldValue = data.getString("oldValue");
        IkDict.STOPWORDS.remove(oldValue);
        IkDict.STOPWORDS.add(newValue);
        fileService.saveJSON(IkDict.STOPWORDS, stopwords);
        log.info("[更新] [停止词词典]: " + oldValue + " 改为:" + newValue);
        return "更新成功";
    }

    /**
     * 删除扩展词典
     * @param data
     * @return
     */
    @DeleteMapping("/dict")
    public String delDict(@RequestBody JSONObject data) {
        if (!data.containsKey("value"))
            return "参数错误";
        String value = data.getString("value");
        IkDict.DICT.remove(value);
        fileService.saveJSON(IkDict.DICT, dict);
        log.info("[删除] [  扩展词典]: " + value);
        return "删除成功";
    }

    /**
     * 删除停止词词典
     * @param data
     * @return
     */
    @DeleteMapping("/stopwords")
    public String delStopwords(@RequestBody JSONObject data) {
        if (!data.containsKey("value"))
            return "参数错误";
        String value = data.getString("value");
        IkDict.STOPWORDS.remove(value);
        fileService.saveJSON(IkDict.STOPWORDS, stopwords);
        log.info("[删除] [停止词词典]: " + value);
        return "删除成功";
    }

}
