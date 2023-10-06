import java.io.File;
import java.io.IOException;

public class GetFileNames {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\develop\\projects\\eclipse-workspace");
        getFileName(file);
    }
    /**
     * 获取所有.java结尾的文件路径
     * @param file
     * @throws IOException
     */
    public static void getFileName(File file) throws IOException {
        if(!file.exists()) {
            throw new IOException("路径不存在");
        }
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File f : files) {
                getFileName(f);
            } 
        } else {
            if(file.getName().endsWith(".java")) {
                System.out.println(file.getAbsolutePath());
            }
      
        }
    }
}
