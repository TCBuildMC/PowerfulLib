package xyz.tcbuildmc.common.powerfullib.util.impl.io;

import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.powerfullib.util.impl.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@ApiStatus.Experimental
public class GZipUtils {
    public static void compress(File gzip, File... sources) {
        try (FileOutputStream fos = new FileOutputStream(gzip);
             GZIPOutputStream gos = new GZIPOutputStream(fos)) {

            for (File source : sources) {
                if (source.isDirectory()) {
                    compressDirectory(gos, source);
                } else {
                    compressFile(gos, source);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to create gzip file. " + e);
        }
    }

    private static void compressDirectory(GZIPOutputStream gos, File directory) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    compressDirectory(gos, file);
                } else {
                    compressFile(gos, file);
                }
            }
        }
    }

    private static void compressFile(GZIPOutputStream gos, File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            Utils.write(fis, gos);
        }
    }

    public static void extract(File gzip, File destination) {
        try (FileInputStream fis = new FileInputStream(gzip);
             GZIPInputStream gis = new GZIPInputStream(fis);
             FileOutputStream fos = new FileOutputStream(destination)) {

            Utils.write(gis, fos);
        } catch (IOException e) {
            System.err.println("Failed to extract gzip file. " + e);
        }
    }

    public static void main(String[] args) {
        compress(new File("test.tar.gz"), new File("test"));
    }
}
