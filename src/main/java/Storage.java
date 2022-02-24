import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    public Storage() {
    }

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
                output.append(tasks.get(0)).append("\n");
            }

            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
