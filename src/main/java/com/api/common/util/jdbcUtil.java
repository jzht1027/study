package com.api.common.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @ClassName jdbcUtil
 * @Description
 * @Author
 * @Date 2021/5/17 10:20
 * @Version 1.0
 **/
@Configurable
public class jdbcUtil {
    /**
     * @Description: spring 容器注入druid 数据源
     * @Date: 2021/5/17 11:10
     * @param: []
     * @return: javax.sql.DataSource druid 数据源
     **/
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public static DataSource druidDataSource(){
//        return new DruidDataSource();
//    }

    public static boolean getLoginJdbc() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.150.120:1521/ODTSDB";
        String userName = "eds_uat";
        String password = "abc123";

        String sql = "select COUNTERPARTYID from EDS_COUNTERPARTY where COUNTERPARTYID ='10007'";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getString("COUNTERPARTYID"));
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return true;
    }
}
