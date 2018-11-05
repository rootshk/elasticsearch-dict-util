package top.roothk.ikdemo.controller;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.roothk.ikdemo.bean.IkDict;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ES远程更新词库接口
 */
@Slf4j
@RestController
@RequestMapping("/ik-server")
public class IkController {

    @GetMapping("/dict")
    public String getDict(HttpServletRequest request, HttpServletResponse response) {
        return get(IkDict.DICT, request, response);
    }

    @GetMapping("/stopwords")
    public String getStopwords(HttpServletRequest request, HttpServletResponse response) {
        return get(IkDict.STOPWORDS, request, response);
    }

    private String get(JSONArray keywords, HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        try {

            StringBuilder sb = new StringBuilder();
            String eTag = request.getHeader("If-None-Match");
            Long modified= request.getDateHeader("If-Modified-Since");

            //设置头
            if(null == modified || -1 == modified) {
                //如果没有，则使用当前时间
                modified = System.currentTimeMillis();
            }

            // 设置头信息。
            String nowEtag = DigestUtils.md5DigestAsHex(keywords.toJSONString().getBytes());
            response.setDateHeader("Last-Modified", Long.valueOf(modified));
            response.setHeader("ETag", DigestUtils.md5DigestAsHex(keywords.toJSONString().getBytes()));

            if(!nowEtag.equals(eTag)) {
                //拼装结果
                for(int i = 0; i < keywords.size(); i++) {
                    String key = keywords.getString(i);
                    if(!StringUtils.isEmpty(keywords)){
                        //分词之间以换行符连接
                        if(!StringUtils.isEmpty(sb.toString())) {
                            sb.append("\r\n");
                        }
                        sb.append(key);
                    }
                }
                //result = new String(result.getBytes("ISO8859-1"), "UTF-8");
                result = sb.toString();
                //更新时间
                response.setDateHeader("Last-Modified", System.currentTimeMillis());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
