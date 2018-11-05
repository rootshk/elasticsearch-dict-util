package top.roothk.ikdemo.config;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.roothk.ikdemo.bean.IkDict;
import top.roothk.ikdemo.entity.Allow;
import top.roothk.ikdemo.entity.NotAllowed;
import top.roothk.ikdemo.repository.AllowRepository;
import top.roothk.ikdemo.repository.NotAllowedRepository;
import top.roothk.ikdemo.service.FileService;

import java.util.List;

@Slf4j
@Component
public class InitConfig implements CommandLineRunner {

    @Autowired
    private FileService fileService;
    @Autowired
    private AllowRepository allowRepository;
    @Autowired
    private NotAllowedRepository notAllowedRepository;

    @Value("${ik.file-switch}")
    private Boolean fileSwitch;

    @Override
    public void run(String... args) throws Exception {
        if (fileSwitch) {
            try {
                //具体初始化
                IkDict.DICT = fileService.getDictJSON();
                IkDict.STOPWORDS = fileService.getStopwordsJSON();
            } catch (Exception e) {
                //错误处理
            }
        } else {
            List<Allow> allowList =  allowRepository.findAll();
            List<NotAllowed> notAllowedsList =  notAllowedRepository.findAll();
            JSONArray allows = new JSONArray();
            JSONArray notAlloweds = new JSONArray();
            allowList.forEach(allow -> allows.add(allow.getName()));
            notAllowedsList.forEach(notAllowed -> notAlloweds.add(notAllowed.getName()));
            IkDict.DICT = allows;
            IkDict.STOPWORDS = notAlloweds;
        }
        log.info("------------------------- >> Spring Boot 初始化完成 >>");
    }
}