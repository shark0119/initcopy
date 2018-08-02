package cn.com.unary.initcopy.dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 获取数据库连接的数据源
 * @author shark
 *
 */
public class DataSource implements Closeable{

	private static DruidDataSource dds;
	private final static String DRIVER = "org.sqlite.JDBC";
	private static String URL ;
	
	private volatile boolean inited = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public DataSource () {}
	public void init (String dbPos) throws SQLException {
		if (inited)
			return;
		lock.lock();
		URL = "jdbc:sqlite:" + dbPos; // "jdbc:sqlite:G:\\sqliteDB\\test.db"
		dds = new DruidDataSource();
		dds.setUrl(URL);
		dds.setInitialSize(1);
		dds.setMinIdle(1);
		dds.setMaxActive(20);
		dds.setMaxWait(60_000);
		dds.setTimeBetweenEvictionRunsMillis(60_000);
		dds.setMinEvictableIdleTimeMillis(300_000);
		dds.setValidationQuery("SELECT 'x'");
		dds.setTestWhileIdle(true);
		dds.setTestOnBorrow(false);
		dds.setTestOnReturn(false);
		dds.setFilters("stat");
		dds.setDriverClassName(DRIVER);
		dds.init();
		inited = true;
		lock.unlock();
	}

	public Connection getConnection () throws SQLException {
		return dds.getConnection();
	}
	@Override
	public void close() throws IOException {
		dds.close();
	}
	
}
