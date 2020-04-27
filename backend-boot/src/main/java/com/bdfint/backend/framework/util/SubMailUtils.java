package com.bdfint.backend.framework.util;

import com.bdfint.backend.framework.common.Global;
import com.opensymphony.module.sitemesh.mapper.ConfigLoader;
//import com.submail.lib.MESSAGEXsend;
//import com.submail.config.AppConfig;
//import com.submail.utils.ConfigLoader;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by lsl on 2018/3/20.
 */
public class SubMailUtils {
    public static String send(String to, String code) {
//        AppConfig config = ConfigLoader.createConfig(Global.getMsgAppid(), Global.getMsgAppkey(), Global.getMsgSigntype());
//        MESSAGEXsend submail = new MESSAGEXsend(config);
//        submail.addTo(to);
//        submail.setProject(Global.getMsgProject());
//        submail.addVar("code", code);
//        submail.addVar("minute", "5");
//        String response = null;
//        try {
//            response = submail.xsend();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("获取到的消息：" + response);
//        return response;
        return "";
    }

    public static String randomCode(){
        char[] codeSeq = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
            s.append(r);
        }
        return s.toString();
    }
}
