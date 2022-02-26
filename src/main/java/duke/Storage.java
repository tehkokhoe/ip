package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    public Storage() {
    }

    /**
     * Saves the task list in a file.
     *
     * @param tasks list of tasks saved.
     */
    public void save(ArrayList<String> tasks) {
        String working = System.getProperty("user.dir");
        Path path = Paths.get(working, "data");
        path.toFile().mkdirs();
        File file = new File(path + "/duke.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < tasks.size(); i++) {
                output.append(tasks.get(i)).append("\n");
            }

            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns data out of saved file as an {@link ArrayList}.
     *
     * @return the list of entries retrieved from save file.
     * @throws DukeException If file could not be used or created.
     */
    public ArrayList<String> load() throws DukeException {
        String working = System.getProperty("user.dir");
        Path path = Paths.get(working, "data");
        path.toFile().mkdirs();
        File file = new File(path + "/duke.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Saved task list could not be loaded");
        }

        ArrayList<String> input = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String entry = reader.readLine();

            while (entry != null) {
                input.add(entry);
                entry = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DukeException("Saved task list could not be loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }
}
