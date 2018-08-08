package ml.sadriev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RawCleaner {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please, specify the path to directory for cleaning it.");
            System.exit(0);
        } else {
            rawDeleter(args[0]); //здесь будет разбор командной строки
        }
    }

    /**
     * Корневой метод программы
     * @param path Путь к корневой папке с фотографиями
     */
    private static void rawDeleter(String path) {
        List<File> cr2FilesForDelete = getCr2Files(path);
        deleteCr2Files(path, cr2FilesForDelete);
    }

    /**
     * Удаление cr2 без пар jpg
     * @param path Путь к jpg-файлам
     * @param cr2FilesForDelete список cr2-Файлов из папки RAW
     */
    private static void deleteCr2Files(String path, List<File> cr2FilesForDelete) {
        int countDeleted = 0;

        for (File file : cr2FilesForDelete) {
            String fileName = file.getName().substring(0, file.getName().length() - 3);
            File jpgFile = new File(path + "\\" + fileName + "jpg");
            File cr2File = new File(path + "\\RAW\\" + fileName + "cr2");
            if (!jpgFile.exists()) {
                cr2File.delete();
                countDeleted++;
                System.out.println("File " + cr2File.getName() + " is deleted");
            }
        }

        System.out.println(countDeleted + " files deleted.");
    }

    /**
     * Получение списка всех cr2-файлов
     * @param path Путь к cr2-файлам
     * @return список файлов в папке RAW
     */
    private static List<File> getCr2Files(String path) {
        File cr2Files[] = new File(path + "\\RAW").listFiles();
        List<File> cr2FilesForDelete = new ArrayList<>();

        for (File dirOrFile : cr2Files) {
            if (dirOrFile.isFile() && dirOrFile.getName().toLowerCase().endsWith("cr2")) {
                cr2FilesForDelete.add(dirOrFile);
                System.out.println(dirOrFile.getName());
            }
        }
        return cr2FilesForDelete;
    }
}
