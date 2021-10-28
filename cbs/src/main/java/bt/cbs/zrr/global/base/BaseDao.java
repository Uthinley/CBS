package bt.cbs.zrr.global.base;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Properties;


public abstract class BaseDao {
    protected String sqlQuery;
    protected EntityManager em;
    @Autowired
    @Qualifier("queryProps")
    protected Properties properties;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

    protected org.hibernate.query.Query hibernateQuery(String query, Class dtoClazz) {
        return getCurrentSession()
                .createSQLQuery(query)
                .setResultTransformer(new CaseLessAliasToBeanResultTransformer(dtoClazz));
}

    protected org.hibernate.Query hibernateQuery(String query) {
        return getCurrentSession().createSQLQuery(query);
    }

    protected Query persistenceQuery(String query, Class entityClazz) {
        return em.createNativeQuery(query, entityClazz);
    }

    protected Query persistenceQuery(String query) {
        return em.createNativeQuery(query);
    }

    protected void saveOrUpdate(Object obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    protected void deleteE(Object obj) {
        em.remove(obj);
    }
}




























