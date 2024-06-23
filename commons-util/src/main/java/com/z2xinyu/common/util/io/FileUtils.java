package com.z2xinyu.common.util.io;

/**
 * @author zhangxinyu
 * @date 2024/6/17
 **/
public class FileUtils {

/*    public static File convertMultipartFileToFile(MultipartFile  file) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀(.xml)
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 若要防止生成的临时文件重复,需要在文件名后添加随机码
        try {
            //"tmp", ".txt"
            //fileName这块可以根据自己的业务需求进行修改，我这里没有做任何处理
            File file = File.createTempFile(fileName, suffix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            logger.error("MultipartFile转File失败", e);
        }
        return null;

        //在业务侧写代码时记得处理掉这个临时文件
        if (file != null) {
            // 将指定的文件标记为“删除待定”(JVM会注册一个关闭钩子（Shutdown Hook）),JVM退出时，会检查所有被标记为“删除待定”的文件，并尝试删除它们
            // file.deleteOnExit();
            file.delete();

        }
    }*/

}
