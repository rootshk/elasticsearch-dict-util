package top.roothk.ikdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.roothk.ikdemo.job.DictJob;

import javax.annotation.PreDestroy;

@Slf4j
@Component
public class KillPreDestroy {

    @Autowired
    private DictJob dictJob;

    @PreDestroy
    public void destory() {
        log.info("----------->> 程序结束前的持久化操作");
        dictJob.saveDict();
    }
}
