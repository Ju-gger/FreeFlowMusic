package ffm.freeflowmusic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectSettings {
    public static void save(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/ffm/freeflowmusic/settings.txt"));
            writer.write("Write to file test");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean settingsExists(){
        return new File("src/main/resources/ffm/freeflowmusic/settings.txt").exists();
    }
}
