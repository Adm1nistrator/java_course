package task5_2;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Adm1n on 01.06.2016.
 */
public class ClientConnection implements Runnable {
    Socket clientSocket;
    String catalogName;
    String rootPath;

    ClientConnection(Socket clientSocket, String catalogName, String rootPath) {
        this.clientSocket = clientSocket;
        this.catalogName = catalogName;
        this.rootPath = rootPath;
    }

    @Override
    public void run() {

        //пишем статус ответа
        OutputStream outputStream = null;
        try {
            outputStream = clientSocket.getOutputStream();
            writeResponse(catalogName, rootPath, outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void writeResponse(@NotNull String catalogName, String rootPath, OutputStream outputStream) throws IOException {
        if (catalogName == null) {
            throw new IllegalArgumentException("Имя каталога не заданно");
        }
        File current = new File(catalogName);

        if (!current.exists()) {
            String pageNotFound = HtmlCreator.pageNotFound();
            outputStream.write(HtmlCreator.pageNotFoundHead(HtmlCreator.getBodyLength(pageNotFound)).getBytes());
            outputStream.write(pageNotFound.getBytes());
            outputStream.flush();
            return;
        }

        if (current.isDirectory()) {
            System.out.println("Текйщий каталог :" + current);
            String directoryHtml = HtmlCreator.renderDirectoryHtml(current, rootPath);
            outputStream.write(HtmlCreator.directoryHead(HtmlCreator.getBodyLength(directoryHtml)).getBytes());
            outputStream.write(directoryHtml.getBytes());
            outputStream.flush();
            return;
        }
        System.out.println("Текйщий фаил :" + current.length());
        outputStream.write(HtmlCreator.fileHead(HtmlCreator.getBodyLength(HtmlCreator.getFile(catalogName))).getBytes());
        outputStream.write(HtmlCreator.getFile(catalogName));

        return;


    }
}
