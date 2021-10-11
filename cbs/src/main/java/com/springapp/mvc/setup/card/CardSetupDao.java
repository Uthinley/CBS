package com.springapp.mvc.setup.card;

import com.springapp.mvc.card.CardDTO;
import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.global.dto.DropdownDTO;
import com.springapp.mvc.setup.group.GroupSetup;
import com.springapp.mvc.setup.group.GroupSetup_A;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CardSetupDao extends BaseDao {

    private Query hQuery;

    @Transactional(rollbackFor = Exception.class)
    public void save(CardSetup cardSetup) {
        if (!StringUtils.isEmpty(cardSetup)) {
            saveOrUpdate(cardSetup);
        }
    }

    @Transactional(readOnly = true)
    public List<CardSetupDTO> getCardList() {
        sqlQuery = properties.getProperty("CardDao.getCardList");
        hQuery = (Query) hibernateQuery(sqlQuery, CardSetupDTO.class);
        return hQuery.list();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(int id) {
        sqlQuery = properties.getProperty("CardDao.delete");
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery).setParameter("id",id);
        hQuery.executeUpdate();
    }


}
