package task5_2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Adm1n on 01.06.2016.
 */
public class ClientConnection implements Runnable {
    Socket clientSocket;
    String catalogName;

    ClientConnection (Socket clientSocket, String catalogName){
        this.clientSocket = clientSocket;
        this.catalogName= catalogName;
    }

    @Override
    public void run() {

        //пишем статус ответа
        OutputStream outputStream = null;
        try {
            outputStream = clientSocket.getOutputStream();
            //минимально необходимые заголовки и длина
            outputStream.write("HTTP/1.0 200 OK\r\n".getBytes());
            outputStream.write(HtmlCreator.head(catalogName).getBytes());
            Integer length = HtmlCreator.generatHtml(catalogName).length();
            outputStream.write(("Content-Length: " + length + "\r\n").getBytes());
            //пустая строка отделяет заголовки
            outputStream.write("\r\n".getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(HtmlCreator.generatHtml(catalogName).getBytes());
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
