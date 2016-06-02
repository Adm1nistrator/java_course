package task5_2;

import com.sun.istack.internal.NotNull;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Adm1n on 30.05.2016.
 */
class HtmlCreator {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-YYYY HH:mm");

    static String generatHtml(@NotNull String catalogName) {
        if (catalogName == null) {
            throw new IllegalArgumentException("Имя каталога не заданно");
        } else {
            File current = new File(catalogName);
            if (current.isDirectory()) {
                return renderDirectoryHtml(getFileList(current));
            }
            return renderFileHtml(current);
        }

    }

  /*  private static boolean DirOrFile(String catalogName) {
        File current = new File(catalogName);
        if (current.isDirectory()) {
            return true;
        } else return false;

    }*/

    static String head(String catalogName) {
        StringBuilder out = new StringBuilder();
        Integer length = HtmlCreator.generatHtml(catalogName).length();
        out.append("HTTP/1.0 200 OK\r\n");

        out.append("Content-Length: ").append(length).append("\r\n");

        File current = new File(catalogName);
        if (current.isDirectory()) {
            out.append("Content-Type: text/html; charset=utf-8\r\n");
            out.append("\r\n");
            out.append("\r\n");
            return out.toString();
        }else {
            out.append("Content-Type:  application/octet-stream\r\n");
            out.append("\r\n");
            out.append("\r\n");
            return out.toString();

        }

    }

    private static String renderDirectoryHtml(List<File> fileList) {

        Collections.sort(fileList, new FileComparator());

        StringBuilder out = new StringBuilder("");
        out.append("<html><body>");
        String parent = fileList.get(0).getParentFile().getParent();
        out.append("<a href='").append(parent).append("'> ../</a>");
        out.append("<table width='100%'>");

        for (File file : fileList) {
            String fileLink = "<a href='" + file.getAbsolutePath() + "'>" + file.getName() + (file.isDirectory() ? "/" : "") + "</a>";
            String fileLastModified = DATE_FORMAT.format(file.lastModified());
            String fileSize = file.isFile() ? getSizeFile(file) : "-";
            out.append("<tr><td>").append(fileLink).append("</td>");
            out.append("<td>").append(fileLastModified).append("</td>");
            out.append("<td>").append(fileSize).append("</td></tr>");
        }

        out.append("</table></body></html>");

        return out.toString();
    }

    private static String renderFileHtml(File currentFile) {
        StringBuilder out = new StringBuilder("");
        out.append("Mime Type of ").append(currentFile.getName()).append(" is ");
        out.append(new MimetypesFileTypeMap().getContentType(currentFile));
        if (currentFile.isFile()) {
            return out.toString();
        } else return "";

    }

    private static List<File> getFileList(File current) {
        List<File> result = new ArrayList<>();
        if (current.exists() & current.isDirectory()) {
            File[] files = current.listFiles();
            if (files == null) {
                return result;
            } else {
                Collections.addAll(result, files);
            }

        }
        return result;
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
        } else return bytes + " B";
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

