package task5_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by anykey on 26.05.16.
 */
public class Explorer {

    public static void main(String[] args) throws IOException {
        File f = new File("icon.png");
        //   String catalogName = "//home//anykey//temp/";
        String catalogName = "//home//anykey//IdeaProjects//java_course//";
        //сервер
        int port;
        port = 8888;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();

            //читаем первую строку запроса, игнорируем все заголовки которые идут дальше первой строки

            StringBuilder sb = new StringBuilder();

            int c;

            while ((c = inputStream.read()) != -1 && c != 10 && c != 13) {

                sb.append((char) c);

            }
            //получаем команду и ее аргументы
            String data = sb.toString();
            String arg[] = data.split(" ");
            String cmd = arg[1].trim().toUpperCase();
            //пишем статус ответа
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("HTTP/1.0 200 OK\r\n".getBytes());
            //минимально необходимые заголовки и длина
            outputStream.write("Content-Type: text/html\r\n".getBytes());
            Integer length = HtmlCreator.generatHtml(catalogName).length();
            outputStream.write(("Content-Length: " + length + "\r\n").getBytes());
            //пустая строка отделяет заголовки
            outputStream.write("\r\n".getBytes());
            System.out.println(cmd);
            outputStream.write(HtmlCreator.generatHtml(catalogName).getBytes());
            outputStream.flush();
            inputStream.close();
            accept.close();


        }
    }

}





