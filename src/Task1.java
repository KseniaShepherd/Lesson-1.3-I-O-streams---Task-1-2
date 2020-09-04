import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Task1 {
    private static final StringBuilder LOG = new StringBuilder();
    private static final String PATH = "/Users/kseniashepherd/Games";

    public static void main(String[] args) {
        createDir(PATH, "src");
        createDir(PATH, "res");
        createDir(PATH, "savegames");
        createDir(PATH, "temp");
        createDir(PATH + "/src", "main");
        createDir(PATH + "/src", "test");
        createFile(PATH + "/src" + "/main", "Main.java");
        createFile(PATH + "/src" + "/main", "Utils.java");
        createDir(PATH + "/res", "drawables");
        createDir(PATH + "/res", "vectors");
        createDir(PATH + "/res", "icons");
        createFileWithText(PATH + "/temp", "temp.txt", LOG.toString());

    }

    public static void createDir(String path, String name) {
        File file = new File(path, name);
        if (file.mkdir()) {
            LOG.append("Директория ").append(name).append(" создана").append('\n');
        }
    }


    public static void createFile(String path, String name) {
        File file = new File(path, name);

        try {
            if (file.createNewFile()) {
                LOG.append("Файл ").append(name).append(" создан").append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFileWithText(String path, String name, String text) {
        try (FileWriter writer = new FileWriter(path + "/" + name, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
