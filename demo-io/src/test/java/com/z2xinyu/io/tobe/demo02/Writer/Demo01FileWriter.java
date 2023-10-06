package com.z2xinyu.io.tobe.demo02.Writer;

import java.io.FileWriter;
import java.io.IOException;

public class Demo01FileWriter {

    public static void main(String[] args) throws IOException {
        String path = "File.txt";
        FileWriter fw = new FileWriter(path);

        // fw.write(97);
        fw.write(98);
        fw.write(99);

        char[] c = {'z', 'c', 'd'};
        fw.write(c);
        fw.write("刘亦菲");

        fw.close();

    }
}
