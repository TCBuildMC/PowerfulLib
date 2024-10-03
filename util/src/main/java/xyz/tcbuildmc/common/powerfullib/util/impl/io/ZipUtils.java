package xyz.tcbuildmc.common.powerfullib.util.impl.io;

import xyz.tcbuildmc.common.powerfullib.util.impl.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    public static void compress(File zip, File... sources) {
        compress(6, zip, sources);
    }

    public static void compress(int level, File zip, File... sources) {
        try (FileOutputStream fos = new FileOutputStream(zip);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            zos.setLevel(level);

            for (File source : sources) {
                if (source.isDirectory()) {
                    compressDirectory(zos, source, source.getName());
                } else {
                    compressFile(zos, source, source.getName());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to create zip file. " + e);
        }
    }

    private static void compressDirectory(ZipOutputStream zos, File directory, String directoryName) throws IOException {
        String[] children = directory.list();
        if (children == null) {
            return;
        }

        for (String child : children) {
            File f = new File(directory, child);
            if (f.isDirectory()) {
                compressDirectory(zos, f, directoryName + "/" + child);
            } else {
                compressFile(zos, f, directoryName + "/" + child);
            }
        }
    }

    private static void compressFile(ZipOutputStream zos, File file, String fileName) throws IOException {
        zos.putNextEntry(new ZipEntry(fileName));
        try (FileInputStream fis = new FileInputStream(file)) {
            Utils.write(fis, zos);
        }
        zos.closeEntry();
    }

    public static void extract(File zip, File destination) {
        try (FileInputStream fis = new FileInputStream(zip);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File f = new File(destination, zipEntry.getName());
                if (zipEntry.isDirectory()){
                    f.mkdirs();
                } else {
                    if (!f.getParentFile().exists()) {
                        f.getParentFile().mkdirs();
                    }

                    try (FileOutputStream fos = new FileOutputStream(f)) {
                        Utils.write(zis, fos);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to extract zip file. " + e);
        }
    }
}
