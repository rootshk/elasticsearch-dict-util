package top.roothk.ikdemo.job;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.roothk.ikdemo.bean.IkDict;
import top.roothk.ikdemo.entity.Allow;
import top.roothk.ikdemo.entity.NotAllowed;
import top.roothk.ikdemo.repository.AllowRepository;
import top.roothk.ikdemo.repository.NotAllowedRepository;

@Slf4j
@Component
public class DictJob {

    @Autowired
    private AllowRepository allowRepository;
    @Autowired
    private NotAllowedRepository notAllowedRepository;

    @Scheduled(cron = "0 0 1 * * ?")
    public void saveDict() {
        if (IkDict.DICT.size() > 0) {
            log.info("--------->> 扩展字典持久化操作");
            allowRepository.deleteAll();
            log.info("--------->> 扩展字典初始化");
            for (int i = 0; i < IkDict.DICT.size(); i++) {
                String name = IkDict.DICT.getString(i);
                Allow allow = new Allow();
                allow.setName(name);
                allowRepository.save(allow);
            }
            log.info("--------->> 扩展字典持久化完成");
        }
        if (IkDict.STOPWORDS.size() > 0) {
            log.info("--------->> 排除字典持久化操作");
            notAllowedRepository.deleteAll();
            log.info("--------->> 排除字典初始化");
            for (int i = 0; i < IkDict.STOPWORDS.size(); i++) {
                String name = IkDict.STOPWORDS.getString(i);
                NotAllowed notAllowed = new NotAllowed();
                notAllowed.setName(name);
                notAllowedRepository.save(notAllowed);
            }
            log.info("--------->> 排除字典持久化完成");
        }
    }

}
