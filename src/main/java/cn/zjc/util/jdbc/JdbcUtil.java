package cn.zjc.util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {

    private JdbcUtil() {
    }

    private static DataSource dataSource;

    public static final int minSize = 1;

    public static final int maxSize = 5;

    // 初始化数据库连接池
    static {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();

            Properties properties = new Properties();
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("config/properties/jdbc.properties"));

            cpds.setUser(properties.getProperty("jdbc.username"));
            cpds.setPassword(properties.getProperty("jdbc.password"));
            cpds.setJdbcUrl(properties.getProperty("jdbc.url"));
            cpds.setDriverClass(properties.getProperty("jdbc.driverClassName"));

            cpds.setMinPoolSize(minSize);
            cpds.setMaxPoolSize(maxSize);

            dataSource = cpds;
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    // 获得数据库连接池
    public static DataSource getDataSource() {
        return dataSource;
    }

    // 获得连接,找jdcp要连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 释放资源
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }

            if (stmt != null) {
                stmt.close();
                stmt = null;
            }

            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            System.out.println("连接关闭异常");
            e.printStackTrace();
        }
    }

    // 通用的更新方法，可以做增删改操作,所有的操作只有sql和参数不一样
    public static boolean update(String sql, Object[] params) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            int count = pstmt.executeUpdate();
            if (count > 0)
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            release(rs, pstmt, conn);
        }
    }

    // 通用的查询方法
    public static Object query(String sql, Object[] params, ResultSetHandler handler) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; params != null && i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            // 获得结果集
            rs = pstmt.executeQuery();
            // 调用结果集的处理方式 ,处理结果集
            Object value = handler.handle(rs);

            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            release(rs, pstmt, conn);
        }
    }

}
