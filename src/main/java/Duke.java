import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList tasks;
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.startScreen();
        boolean isRunning = true;
        while (isRunning) {
            String[] inputs = Parser.parseInput(ui);
            Command cmd = Parser.parseCommand(inputs);
            UI.startLine();
            this.execute(cmd, inputs);
            isRunning = cmd.isRunning();
            UI.endLine();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void execute(Command cmd, String[] inputs) {
        try {
            switch (cmd) {
                case BYE:
                    ui.byeDisplay();
                    break;
                case LIST:
                    tasks.list();
                    break;
                case MARK:
                    tasks.mark(inputs);
                    storage.save(tasks.getTasks());
                    break;
                case UNMARK:
                    tasks.unmark(inputs);
                    storage.save(tasks.getTasks());
                    break;
                case TODO:
                    tasks.addToDo(inputs);
                    storage.save(tasks.getTasks());
                    break;
                case DEADLINE:
                    tasks.addDeadline(inputs);
                    storage.save(tasks.getTasks());
                    break;
                case EVENT:
                    tasks.addEvent(inputs);
                    storage.save(tasks.getTasks());
                    break;
                case DELETE:
                    tasks.delete(inputs);
                    storage.save(tasks.getTasks());
                    break;
                case DATEFORMAT:
                    ui.showDateFormats();
                    break;
            }
        } catch (DukeException err) {
            ui.showError(err.getMessage());
        } catch (NumberFormatException err) {
            ui.showError(UI.getIndent() + "☹ OOPS!!! Task number given is not suitable");
        }
    }
}