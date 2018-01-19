package time_tracking.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/time_tracking_db");
                    ds.setUsername("root");
                    ds.setPassword("1111");
                    ds.setMinIdle(10);
                    ds.setMaxIdle(20);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                    System.out.println("datasource created. Datasource = "+dataSource+". Thread : "+Thread.currentThread().getName());
                }
            }
        }
        return dataSource;

    }


}
