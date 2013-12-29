package com.task.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: 豪杰
 * Date: 13-12-9
 * Time: 上午9:01
 * To change this template use File | Settings | File Templates.
 */
public class Common {
    protected String DBDriver = "com.mysql.jdbc.Driver";

    protected String connectionStr = "jdbc:mysql://localhost:3306/task";

    protected String DBUser = "root";

    protected String DBPwd = "root";

    protected Connection con = null;

    protected Statement stmt = null;

    protected ResultSet rs = null;

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
                             '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    protected void connectDB() {
        try{
            Class.forName(DBDriver).newInstance();;	 //加载驱动器
            con= DriverManager.getConnection(connectionStr, DBUser, DBPwd);
            //连接数据库
            stmt=con.createStatement();   //创建Statement对象
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void closeDB() {
        try{
            stmt.close();    //关闭语句
            con.close();   //关闭连接
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * encode By MD5
     *
     * @param str
     * @return String
     */
    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * encode string
     *
     * @param str
     * @return String
     */
    public static String sha1(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
}
