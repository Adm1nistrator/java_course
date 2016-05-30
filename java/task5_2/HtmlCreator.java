package task5_2;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Adm1n on 30.05.2016.
 */
public class HtmlCreator {
    private static ArrayList<File> fileList = new ArrayList<>();
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-YYYY HH:mm");

    static String generatHtml(@NotNull String catalogName) {
        if (catalogName==null)
        {throw new IllegalArgumentException("Имя каталога не заданно");}

        getFileList(catalogName);

        Collections.sort(fileList, new FileComparator());

        StringBuilder out = new StringBuilder();
        out.append("<html><body>");
        out.append("<a href='").append(fileList.get(1).getParent()).append("'> ../</a>");
        out.append("<table width='100%'>");

        for (File file : fileList) {
            String fileLink = "<a href='" + file.getName() + "'>" + file.getName() + (file.isDirectory() ? "/" : "") + "</a>";
            String fileLastModified = DATE_FORMAT.format(file.lastModified());
            String fileSize = file.isFile() ? getSizeFile(file) : "-";
            out.append("<tr><td>").append(fileLink).append("</td>");
            out.append("<td>").append(fileLastModified).append("</td>");
            out.append("<td>").append(fileSize).append("</td></tr>");
        }

        out.append("</table></body></html>");

        return out.toString();
    }

    private static void getFileList(String catalogName) {
        File files = new File(catalogName);

            for (File file : files.listFiles()) {
                fileList.add(file);
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

    private static class FileComparator implements Comparator<File> {

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
    }
}
