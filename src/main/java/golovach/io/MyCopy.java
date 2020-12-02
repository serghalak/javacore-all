package golovach.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MyCopy {

    public static void main(String[] args) throws IOException {

        URL url=new URL("http://google.com");
        InputStream is = url.openConnection().getInputStream();

        OutputStream os = new FileOutputStream("c:/google.txt");
        copy(is,os);
        System.out.println("done!!!");

        Number number;
    }

    public static void copy(InputStream src, OutputStream dst) throws IOException {

        while(true){
            int data=src.read();
            System.out.println(data);
            if(data==-1)
                break;
            dst.write(data);
        }
    }
}
