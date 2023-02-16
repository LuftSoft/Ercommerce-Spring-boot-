package com.luffschloss.shop.config;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
// Config thông tin database bằng Java file
// Bảo mật hơn so với lưu thông tin config tại file application.properties
//@Configuration
public class DbConfig {
	@Value("${db.datasource.driver-class-name}")
	private String DriverClassName;
	@Value("${db.datasource.url}")
	private String Url;
	@Value("${db.datasource.username}")
	private String UserName;
	@Value("${db.datasource.password}")
	private String Password;
	//cấu hình datasource, sử dụng Hikari datasource
	// một số datasource khác PooledDatasource
	//@Bean(name = "dataSource")
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(DriverClassName);
		config.setJdbcUrl(Url);
		config.setUsername(UserName);
		config.setPassword(Password);
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	//cấu hình để có thể sử dụng các transaction
	//@Bean(name="transactionManager")
	DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	} 
	//Tạo ra các câu truy vấn sql, các câu truy vấn này được thực thi ở tr
//	@Bean(name="sqlSessionFactory")
//	public SqlSessionFactory
}
