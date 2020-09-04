import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Task2 {
    private static final String PATH = "/Users/kseniashepherd/Games/savegames";
    private static final List<GameProgress> SAVES = new ArrayList<>();

    public static void main(String[] args) {
        SAVES.add(new GameProgress(2, 7, 9, 2));
        SAVES.add(new GameProgress(3, 7, 10, 3));
        SAVES.add(new GameProgress(6, 8, 11, 6));
        for (int i = 0; i < SAVES.size(); i++) {
            String path = PATH + "/save" + (i + 1) + ".dat";
            saveGame(path, SAVES.get(i));
        }
        zipFiles("/Users/kseniashepherd/Games/savegames/zip.zip", PATH);
        deleteFile(PATH);
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathZip, String path) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip));
             FileInputStream fis = new FileInputStream(path);) {
            ZipEntry entry1 = new ZipEntry("savegames");
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(String path) {
        for (int i = 1; i < SAVES.size() + 1; i++) {
            File file = new File(path + "/save" + i + ".dat");
            if (file.delete()) {
                System.out.println(path + " файл удален");
            } else System.out.println("Файла " + path + " не обнаружено");
        }
    }
}


