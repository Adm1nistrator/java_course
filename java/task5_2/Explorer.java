package task5_2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

/**
 * Created by anykey on 26.05.16.
 */
public class Explorer {

    public static void main(String[] args) throws IOException {
        File f = new File("icon.png");
        String rootPath = "//home//anykey//IdeaProjects//java_course";
       // String root = "//home//anykey//IdeaProjects//java_course";

       //   String rootPath = "d:\\";
        //сервер
        int port = 8888;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();

            String data = readRequestHead(inputStream);
            if (data.isEmpty()) {
                System.out.println("Заголовок Пустой"); // TODO ошибка 500
                continue;

            }
            //получаем команду и ее аргументы
            String arg[] = data.split(" ");
            String cmd = arg[0].trim().toUpperCase();
            String url = arg[1];
            String catalogName = createPath(url, rootPath);

            System.out.println("Запрошен путь:" + catalogName);
            int i = 0;
            new Thread(new ClientConnection(clientSocket, catalogName, rootPath), "Клиентский поток: " + i).start();
            i++;
        }
    }

    /**
     * читаем первую строку запроса, игнорируем все заголовки которые идут дальше первой строки
     */
    static String readRequestHead(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        int c;
        try {
            while ((c = inputStream.read()) != -1 && c != 10 && c != 13) {
                sb.append((char) c);
            }

        } catch (IOException e) {
            e.printStackTrace(); // TODO ошибка 500
        }
        return sb.toString();

    }

    static String createPath(String url, String rootpath) throws UnsupportedEncodingException {
        String path = url.replace("/", File.separator).replace("%20", " ");

        path = URLDecoder.decode(path, "utf-8");
            if (url.equals("/")) {
                return rootpath;
            } else {
                return rootpath+path;
            }
    }

}





