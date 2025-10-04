package com.company.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtil {

    public static List<String> readLines(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }
        return Files.readAllLines(path);
    }

    /**
     * Verilen içeriği dosyaya yazar. Eğer dosya yoksa oluşturur.
     * Mevcut içerik üzerine yazar (overwrite). Append istenirse farklı overload eklenebilir.
     *
     * @param filePath yazılacak dosya yolu
     * @param content  yazılacak içerik
     * @throws IOException yazma sırasında hata oluşursa fırlatılır
     */
    public static void writeReport(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(
                path,
                content + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }

}
