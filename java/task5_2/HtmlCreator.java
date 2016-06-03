package task5_2;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Adm1n on 30.05.2016.
 */
class HtmlCreator {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-YYYY HH:mm");

    static String pageNotFoundHead(Integer bodyLength) {
        StringBuilder out = new StringBuilder();
        out.append("HTTP/1.1 404\r\n");
        out.append("Server: LocalServer\r\n");
        out.append("Content-Type: text/html; charset=utf-8\r\n");
        out.append("Content-Length: ").append(bodyLength).append("\r\n");
        out.append("\r\n");
        return out.toString();

    }

    static String fileHead(Integer bodyLength) {
        StringBuilder out = new StringBuilder();
        out.append("HTTP/1.1 200 OK\r\n");
        out.append("Server: LocalServer\r\n");
        out.append("Content-Type:  application/octet-stream\r\n");
        // out.append("Content-Type: ").append(new MimetypesFileTypeMap().getContentType(current)).append("\r\n");
        out.append("Content-Length: ").append(bodyLength).append("\r\n");
        out.append("\r\n");
        //  out.append("\r\n");
        return out.toString();
    }

    static String directoryHead(Integer bodyLength) {
        StringBuilder out = new StringBuilder();
        out.append("HTTP/1.1 200 OK\r\n");
        out.append("Server: LocalServer\r\n");
        out.append("Content-Type: text/html; charset=utf-8\r\n");
        out.append("Content-Length: ").append(bodyLength).append("\r\n");
        out.append("\r\n");
        return out.toString();
    }

    static Integer getBodyLength(String body) {
        byte[] b = body.getBytes(StandardCharsets.UTF_8);
        return b.length;
    }

    static Integer getBodyLength(byte[] b) {
        return b.length;
    }

    private static String toUpFolder(File currentFile, String rootPath) throws IOException {
        StringBuilder out = new StringBuilder("");
        if (currentFile.getParentFile()==null)
        {
            return "";
        }
        String parentUrl = extractUrl(currentFile.getParentFile(), rootPath);
        out.append("<a href='").append(parentUrl).append("'> ../</a>");
        System.out.println("Путь к родительской папке: " + parentUrl);

        return out.toString();
    }

    private static String extractUrl(File currentFile, String rootPath) throws IOException {
        String fullPath = currentFile.getAbsolutePath();
        StringBuilder sb = new StringBuilder(fullPath);

        if (!fullPath.startsWith(rootPath)) {
            throw new IllegalStateException("Не допустимыый путь : " + fullPath);
        } else if (fullPath.equals(rootPath))
        {
            return "/";
        } else {
            Integer end = rootPath.length()-2;
            System.out.println("URL : " + sb.delete(0,end));
            return sb.delete(0,end).toString();
        }
    }

    static String renderDirectoryHtml(File currentFile, String rootPath) throws IOException {
        File[] files = currentFile.listFiles();
        List<File> fileList;

        if (files == null) {
            fileList = new ArrayList<>();
        } else {
            fileList = Arrays.asList(files);
        }

        Collections.sort(fileList, new FileComparator());

        StringBuilder out = new StringBuilder("");
        out.append("<!DOCTYPE html>");
        out.append("<html><body>");
        out.append(toUpFolder(currentFile, rootPath));
        out.append("<table width='60%'>");

        for (File file : fileList) {
            String fileLink = "<a href='" + extractUrl(file, rootPath) + "'>" + file.getName() + (file.isDirectory() ? "/" : "") + "</a>";
            String fileLastModified = DATE_FORMAT.format(file.lastModified());
            String fileSize = file.isFile() ? getSizeFile(file) : "-";
            out.append("<tr><td>").append(fileLink).append("</td>");
            out.append("<td>").append(fileLastModified).append("</td>");
            out.append("<td>").append(fileSize).append("</td></tr>");
        }

        out.append("</table></body></html>");

        return out.toString();
    }

    static String renderFileHtml(File currentFile) {
        StringBuilder out = new StringBuilder();

        out.append(new MimetypesFileTypeMap().getContentType(currentFile));
        if (currentFile.isFile()) {
            return out.toString();
        } else return "";

    }

   static byte[] getFile(String catalogName) throws IOException {
        Path path = Paths.get(catalogName);
        byte data[] = Files.readAllBytes(path);

        return data;

    }

    static String pageNotFound() {
        StringBuilder out = new StringBuilder();

        out.append("<!DOCTYPE html>");
        out.append("<html><body>");
        out.append("такой страницы нет");
        out.append("</html></body>");
        return out.toString();
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

