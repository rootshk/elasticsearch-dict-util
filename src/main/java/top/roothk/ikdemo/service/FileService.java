package top.roothk.ikdemo.service;

import com.alibaba.fastjson.JSONArray;

public interface FileService {

    /**
     * 获取扩展字典
     * @return
     */
    JSONArray getDictJSON();

    /**
     * 获取扩展停止词字典
     * @return
     */
    JSONArray getStopwordsJSON();

    /**
     * 保存文件
     * @param o
     */
    void saveJSON(Object o, String name);

}
