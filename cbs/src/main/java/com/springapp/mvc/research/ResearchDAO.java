package com.springapp.mvc.research;

import com.springapp.mvc.global.base.BaseDao;
import com.springapp.mvc.global.dto.CurrentUser;
import com.springapp.mvc.setup.card.CardSetupDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ResearchDAO extends BaseDao {
    private Query hQuery;
    @Transactional
    public void save(ResearchEntity researchEntity) {
        saveOrUpdate(researchEntity);
    }

    @Transactional
    public List<ResearchDTO> getResearchList(CurrentUser currentUser) {
        sqlQuery = properties.getProperty("ResearchDAO.getResearchList");
        hQuery = (Query) hibernateQuery(sqlQuery, ResearchDTO.class)
                .setParameter("userName",currentUser.getUserName());
        return hQuery.list();
    }
}
