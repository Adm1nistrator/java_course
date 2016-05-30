package task5_2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by anykey on 26.05.16.
 */
public class Explorer {

    private static ArrayList<File> listFileNames = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String catalogName = "//";
        getListFiles(catalogName);
        String outputFileName = "index.html";

        File f = new File("icon.png");

        Collections.sort(listFileNames, new Comparator<File>() {

            @Override
            public int compare(File file1, File file2) {
                if (file1.isDirectory() & file2.isDirectory()) {
                    return file1.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
                } else if (file1.isFile() & file2.isFile()) {
                    return file1.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
                } else if (file1.isDirectory() & file2.isFile()) {
                    return -1;
                } else if (file1.isFile() & file2.isDirectory()) {
                    return 1;
                } else return 0;

            }
        });

        //сервер
        int port;
        port = 8888;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            int c;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            while (true) {
                String s1 = bufferedReader.readLine();
                if (s1 == null || s1.length() == 0) {
                    break;
                }
            }
            OutputStream outputStream = accept.getOutputStream();
            String outString;
            outString = ("<html><body>\n");
            outputStream.write(outString.getBytes());
            outString = ("<a href='" +listFileNames.get(1).getParent()+ "'> ../</a>");
            outputStream.write(outString.getBytes());
            outString = ("<table width='100%'>\n");
            outputStream.write(outString.getBytes());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYYY HH:mm");
            //тело

            for (File file : listFileNames) {
                String fileLink= ("<a href='" + file.getName() + "'>" + file.getName() + ((file.isDirectory() ? "/" : "")) + "</a>");
                String fileLastModified = (dateFormat.format(file.lastModified()));
                String fileSize=(file.isFile() ? getSizeFile(file) : "-");
                outString = String.format("%s%s%s" + "%n",
                        "<tr><td>"+fileLink+"</td>",
                        "<td>" + fileLastModified + "</td>",
                        "<td>" + fileSize + "</td></tr>");

                outputStream.write(outString.getBytes());
            }

            outString = ("</table></body></html>");
            outputStream.write(outString.getBytes());

            outputStream.close();
            inputStream.close();
            accept.close();
        }

    }

    private static void getListFiles(String str) {
        File files = new File(str);
        try {
            for (File file : files.listFiles()) {
                listFileNames.add(file);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static String getSizeFile(File file) {
        double bytes;
        double Kb;
        double Mb;
        double Gb;
        bytes = file.length();
        Kb = bytes / 1024;
        Mb = Kb / 1024;
        Gb = Mb / 1024;
        if (bytes >= 0 & bytes < 1024) {
            return String.format("%1$4.2f" + " B", bytes);
        } else if ((Kb >= 1) & (Kb < 1024)) {
            return String.format("%1$4.2f" + " Kb", Kb);
        } else if ((Mb >= 1) & (Mb < 1024)) {
            return String.format("%1$4.2f" + " Mb", Mb);
        } else if ((Gb >= 1) & (Gb < 1024)) {
            return String.format("%1$4.2f" + " Gb", Gb);
        } else return String.format("%1$" + " B", bytes);
    }

}





