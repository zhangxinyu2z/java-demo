package com.z2xinyu.jdbc.bigdata;

import com.xinyu.utils.v2.JdbcUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试jdbc存取大文本（图片、音频等)
 *
 * @author zhangxinyu
 * @create 2021-05-10 16:39
 */
public class BigDataTest {
    /**
     * 将大文本数据保存到数据库中
     *
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void storeBigData() throws SQLException, IOException {
        // 把一个图片数据存到数据库中
        Connection connection = JdbcUtils.getConnection();
        String sql = "insert into bigdata values (?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setString(2, "刘亦菲");
        /*
        第一种方式
         */
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zhang xinyu\\Pictures\\刘亦菲.jpg");
        ps.setBinaryStream(3, fileInputStream);

        /**
         * 第二种方式：将二进制数据包装成Blob
         */
        byte[] bytes = IOUtils.toByteArray(fileInputStream);
        Blob serialBlob = new SerialBlob(bytes);
        ps.setBlob(3, serialBlob);
        ps.executeUpdate();
    }

    /**
     * 读取数据
     *
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void select() throws SQLException, IOException {
        // 读取该图片数据
        Connection conn = JdbcUtils.getConnection();
        String sql = "select * from bigdata";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) { // 结果集的行标默认在第一行的前一行
            String filename = rs.getString("filename");
            String newFileUrl = "C:\\Users\\zhang xinyu\\Desktop\\" + filename + ".jpg";
            Blob data = rs.getBlob("data");
            InputStream inputStream = data.getBinaryStream();
            // inputStream = rs.getBinaryStream("data");
            // FileOutputStream outputStream = new FileOutputStream(newFileUrl);
            // IOUtils.copy(inputStream, outputStream);

            // 第二种方式写入到文件中
            File file = new File(newFileUrl);
            System.out.println(file.length());
            byte[] binaryData = data.getBytes(1, (int)data.length()); // 有效pos为1
            FileUtils.writeByteArrayToFile(file,binaryData);
        }
    }
}
