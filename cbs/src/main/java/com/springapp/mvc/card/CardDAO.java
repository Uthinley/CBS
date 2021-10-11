package com.springapp.mvc.card;

import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.global.dto.DropdownDTO;
import com.springapp.mvc.setup.card.CardSetup;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CardDAO extends BaseDao {
    private Query hQuery;

    @Transactional(readOnly = true)
    public List<CardDTO> search(String cid){
        sqlQuery = properties.getProperty("CardDao.getCardDetails");
        hQuery = (Query) hibernateQuery(sqlQuery, CardDTO.class)
                .setParameter("cid", cid);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<CardDTO> totalCardsBankWise(){
        sqlQuery = properties.getProperty("CardDao.getTotalCardsBankWise");
        hQuery = (Query) hibernateQuery(sqlQuery, CardDTO.class);
        return hQuery.list();
    }

    @Transactional(readOnly = true)
    public List<CardDTO> getAllCardInfoList(){
        sqlQuery = properties.getProperty("CardDao.getAllCardInfoList");
        hQuery = (Query) hibernateQuery(sqlQuery, CardDTO.class);
        return hQuery.list();
    }
    @Transactional(readOnly = true)
    public List<DropdownDTO> getCardType() {
        sqlQuery = properties.getProperty("CardDao.getCardType");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery, DropdownDTO.class);
        return hQuery.list();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(CardEntity cardEntity) {
        if (!StringUtils.isEmpty(cardEntity)) {
            saveOrUpdate(cardEntity);
        }
    }
}
