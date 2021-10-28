package bt.cbs.zrr.global.config;

import org.hibernate.dialect.SQLServer2012Dialect;

/**
 * Created by Dechen Wangdi on 2/18/2020.
 */
public class SqlServerCustomDialect extends SQLServer2012Dialect {
    public SqlServerCustomDialect() {
        super();
        registerHibernateType(-9, "string");
    }
}
