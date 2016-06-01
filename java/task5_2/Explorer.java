package task5_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by anykey on 26.05.16.
 */
public class Explorer {

    public static void main(String[] args) throws IOException {
        File f = new File("icon.png");
        //   String catalogName = "//home//anykey//temp/";
        String catalogName = "d:\\";
        //сервер
        int port;
        port = 8888;
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
            catalogName = createPath(url, catalogName);
            System.out.println("Запрошен путь:" + catalogName);
            new Thread(new ClientConnection(clientSocket, catalogName), "Клиентский поток"+ UUID.randomUUID()).start();

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

    static String createPath(String url, String catalogName) {
        if (url.equals("/")) {
            return catalogName;
        }
        return catalogName + url.replace("/", File.separator).replace("%20", " ");
    }

}





