package ru.lesson6;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //InputStream                   //OutputStream
        //FileInputStream               //FileOutputStream
        //BufferedInputStream           //FileOutputStream
        //ByteArrayInputStream          //ByteArrayOutputStream
        //ObjectInputStream             //ObjectOutputStream

        //====================================================

        //Reader                        //Writer
        //BufferedReader                //BufferedWriter
        //CharArrayReader               //CharArrayWriter

        String FILE_NAME = "some.txt";
        readFile(FILE_NAME);
        someFunc();

    }

    public static String readFile(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.forName("UTF-8")));

            String line;
            StringBuffer sb = new StringBuffer();
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (IOException e) {
            //обработка
            return "";
        } finally {
            if(bufferedReader != null) {
                try{
                    bufferedReader.close();
                }catch (IOException e) {
                    //варнинг
                }
            }
        }
    }

    /* с библиотекой Appache
    public String readFile(String fileName) {
    InputStreamReader in = null;
    try {
        in = new InputStreamReader(new FileInputStream(new File(filename)), Charsets.UTF_8);
        LineIterator it = new LineIterator(in);
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()) {
        sb.append(it.nextLine());
        }
        return sb.toString();
        }catch (IOException e) {
            return "";
        }finally {
        IOUtils.closeQuietly(in);
        }
    */

    public String readFileJava7(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public String readFileJava7_1(String filename) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
            for(String line : lines) {
                sb.append(line);
            }

            return  sb.toString();
        }catch (IOException e) {
            return "";
        }
    }

    public void readFileToConsole(String filename) throws IOException {
        Files.lines(Paths.get(filename), StandardCharsets.UTF_8).forEach(System.out::println);
    }

    public static <ex, e> void someFunc() throws IOException {
        /*Files.createFile(Paths.get("."));
        Files.createFile(Path.of("."));
        Files.createDirectory();
        Files.createDirectories();
        Files.createTempDirectory();
        Files.delete();
        Files.copy();
        Files.move();

        Files.isDirectory();
        Files.exists();

        Files.readString();
        Files.readAllLines();

        Files.write();
        Files.writeString();
        DirectoryStream<Path> paths = Files.newDirectoryStream();*/


        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("."));
            directoryStream.forEach(System.out::println);
        }catch (IOException ignored) {

        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.data"))) { //запись объекта в файл в байтах
            User user = new User("Dima", 35, Gender.MALE);
            oos.writeObject(user);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream((new FileInputStream("user.data"))) {
            User o = ois.readObject();
        }catch (Exception e){
        }

    }

}
