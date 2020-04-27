package com.bdfint.backend.modules.sys.init;

import com.bdfint.backend.modules.sys.bean.CrmNorthCard;
import com.bdfint.backend.modules.sys.mapper.CrmNorthCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther SIMON
 * @date 2020/4/20
 */
@Component
public class ServerInit implements CommandLineRunner {

    public static String[] cards;

    @Autowired
    private CrmNorthCardMapper crmNorthCardMapper;

    public static String[] getCards(){
        return  cards;
    }

    @Override
    public void run(String... strings){
        cards = crmNorthCardMapper.selectAll().stream().map(CrmNorthCard::getCardId).toArray(String[]::new);
    }
}
