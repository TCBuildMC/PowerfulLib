package xyz.tcbuildmc.common.powerfullib.util.impl.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.*;

public class CompressUtils {

    // level: 0~9
    public static void makeZip(File zip, File... sources) {
        makeZip(6, zip, sources);
    }

    public static void makeZip(int level, File zip, File... sources) {
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zip.toPath()))) {
            zos.setLevel(level);
            for (File source : sources) {
                Files.walk(source.toPath())
                        .filter(Files::isDirectory)
                        .forEach(p -> {
                            ZipEntry entry = new ZipEntry(source.toPath().relativize(p).toString());

                            try {
                                zos.putNextEntry(entry);
                                Files.copy(p, zos);
                                zos.closeEntry();
                            } catch (IOException e) {
                                System.err.println("Failed to make zip file. " + e);
                            }
                        });
            }
        } catch (IOException e) {
            System.err.println("Failed to make zip file. " + e);
        }
    }

    public static void extractZip(File zip, File target) {
        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zip.toPath()))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(target, entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }

                    Files.copy(zis, file.toPath());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to extract zip file. " + e);
        }
    }

    public static void makeGzip(File zip, File... sources) {
        makeGzip(6, zip, sources);
    }

    public static void makeGzip(int level, File zip, File... sources) {
        try (GZIPOutputStream gos = new GZIPOutputStream(Files.newOutputStream(zip.toPath()), level)) {
            for (File source : sources) {
                Files.copy(source.toPath(), gos);
            }
        } catch (IOException e) {
            System.err.println("Failed to make gzip file. " + e);
        }
    }

    public static void extractGzip(File zip, File target) {
        try (GZIPInputStream gis = new GZIPInputStream(Files.newInputStream(zip.toPath()))) {
            Files.copy(gis, target.toPath());
        } catch (IOException e) {
            System.err.println("Failed to extract gzip file. " + e);
        }
    }
}
