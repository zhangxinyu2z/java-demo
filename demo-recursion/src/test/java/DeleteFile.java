import java.io.File;
import java.io.IOException;

public class DeleteFile {
    public static void main(String[] args) throws IOException {
        File f = new File("D:\\test");
         deleteFile(f);
    }
    
    /**
     * 删除一个指定的目录
     * @param f
     * @throws IOException
     */
    public static void deleteFile(File f) throws IOException {
        if(!f.exists()) {
            throw new IOException("路径不存在");
        }
        if(f.isDirectory()) {
//            System.out.println(f.getAbsolutePath());
            File[] files=  f.listFiles();
            for(File file : files) {
                deleteFile(file);
            }
            f.delete();
        } else {
//            System.out.println(f.getAbsolutePath());
            f.delete();
        }
    }
}
