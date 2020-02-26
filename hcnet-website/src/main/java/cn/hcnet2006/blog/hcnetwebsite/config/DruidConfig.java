package cn.hcnet2006.blog.hcnetwebsite.config;//package cn.hcnet2006.blog.uploadapk.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//
//@Configuration
//public class DruidConfig {
//
//    @Value("${spring.datasource.url}")
//    private String dbUrl;
//
//    @Value("root")
//    private String username;
//
//    @Value("123456")
//    private String password;
//
//    @Value("com.mysql.cj.jdbc.Driver")
//    private String driverClassName;
//
//    @Value("5")
//    private int initialSize;
//
//    @Value("5")
//    private int minIdle;
//
//    @Value("20")
//    private int maxActive;
//
//    @Value("60000")
//    private int maxWait;
//
//    @Value("60000")
//    private int timeBetweenEvictionRunsMillis;
//
//    @Value("300000")
//    private int minEvictableIdleTimeMillis;
//
//    @Value("SELECT 1 FROM DUAL")
//    private String validationQuery;
//
//    @Value("true")
//    private boolean testWhileIdle;
//
//    @Value("false")
//    private boolean testOnBorrow;
//
//    @Value("false")
//    private boolean testOnReturn;
//
//    @Value("true")
//    private boolean poolPreparedStatements;
//
//    @Value("20")
//    private int maxPoolPreparedStatementPerConnectionSize;
//
//    @Value("stat,wall")
//    private String filters;
//
//    @Value("{spring.datasource.druid.connection-properties}")
//    private String connectionProperties;
//
//    @Bean
//    @Primary
//    public DataSource dataSource(){
//        DruidDataSource datasource = new DruidDataSource();
//
//        datasource.setUrl(this.dbUrl);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//
//        //configuration
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//        datasource.setTimeBetweenEvictionRunsMillis (timeBetweenEvictionRunsMillis);
//        datasource.setMinEvictableIdleTimeMillis (minEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
//        datasource.setTestWhileIdle(testWhileIdle);
//        datasource.setTestOnBorrow(testOnBorrow);
//        datasource.setTestOnReturn(testOnReturn);
//        datasource.setPoolPreparedStatements(poolPreparedStatements);
//        datasource.setMaxPoolPreparedStatementPerConnectionSize (maxPoolPreparedStatementPerConnectionSize);
//        try {
//            datasource.setFilters(filters);
//        } catch (SQLException e) {
//        }
//        datasource.setConnectionProperties(connectionProperties);
//
//        return datasource;
//    }
//}