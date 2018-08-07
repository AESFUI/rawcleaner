package ml.sadriev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            rawDeleter("new-11");
        }

    }

    private static void rawDeleter(String path) {

        //Получение списка всех cr2
        File cr2Files[] = new File(path + "\\RAW").listFiles();
        List<File> cr2FilesForDelete = new ArrayList<>();

        for (File dirOrFile : cr2Files) {
            if (dirOrFile.isFile() && dirOrFile.getName().toLowerCase().endsWith("cr2")) {

                cr2FilesForDelete.add(dirOrFile);
                System.out.println(dirOrFile.getName());
            }
        }

        //Удаление cr2 без пар jpg
        String jpgDir = path;

        for (File file : cr2FilesForDelete) {
            String fileName = file.getName().substring(0, file.getName().length() - 3);
            File jpgFile = new File(jpgDir + "\\" + fileName + "jpg");
            File cr2File = new File(path + "\\RAW\\" + fileName + "cr2");
            if (!jpgFile.exists()) {
                cr2File.delete();
                System.out.println("File " + cr2File.getName() + " is deleted");
            }
        }
    }
}
