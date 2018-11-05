package top.roothk.ikdemo.service.Impl;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.roothk.ikdemo.service.FileService;
import top.roothk.ikdemo.utils.FileUtils;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileUtils fileUtils;

    @Value("${ik.file-switch}")
    private Boolean fileSwitch;

    public JSONArray getDictJSON(){
        if (fileUtils.existsByName("dict")) {
            return fileUtils.findByName("dict");
        }
        return new JSONArray();
    }

    public JSONArray getStopwordsJSON(){
        if (fileUtils.existsByName("stopwords")) {
            return fileUtils.findByName("stopwords");
        }
        return new JSONArray();
    }

    @Override
    public void saveJSON(Object o, String name) {
        if (!fileSwitch)
            return;
        fileUtils.saveJSON(o, name);
    }
}
