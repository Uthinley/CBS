package bt.cbs.zrr.data.db.bankDeposit;

import bt.cbs.zrr.data.db.model.CardTransaction;
import bt.cbs.zrr.global.base.BaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by Dechen Wangdi on 3/25/2020.
 */

@Repository
public class BankDepositDao extends BaseDao {

    //region public methods.
    @Transactional(readOnly = true)
    public BigDecimal getNextId() {
        sqlQuery = "SELECT (A.ID+1) nID from tblcardtransaction A order by A.ID DESC";
        Query hQuery = hibernateQuery(sqlQuery);
        return hQuery.list().isEmpty() ? BigDecimal.ONE : (BigDecimal) hQuery.list().get(0);
    }

    @Transactional
    public void save(CardTransaction cardTransaction) {
//        em.persist(cardTransaction);
        saveOrUpdate(cardTransaction);
    }
    //endregion
}
