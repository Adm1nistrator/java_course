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
        String catalogName = "d:/";

        //сервер
        int port;
        port = 8888;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String s1 = bufferedReader.readLine();
                if (s1 == null || s1.length() == 0) {
                    break;
                }
            }

            OutputStream outputStream = accept.getOutputStream();
            outputStream.write(HtmlCreator.generatHtml(catalogName).getBytes());

            outputStream.close();
            inputStream.close();
            accept.close();
        }

    }

}





