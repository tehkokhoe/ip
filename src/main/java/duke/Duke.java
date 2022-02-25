package duke;

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
            UI.startLine();

            try {
                Command cmd = Parser.parseCommand(inputs);
                this.execute(cmd, inputs);
                isRunning = cmd.isRunning();
            } catch (IllegalArgumentException e) {
                ui.showError(UI.getIndent() + "Invalid Command: " + inputs[0]);
            }

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
            default:
                throw new IllegalStateException(UI.getIndent() + "Unexpected value: " + cmd);
            }
        } catch (NumberFormatException e) {
            ui.showError(UI.getIndent() + "☹ OOPS!!! Task number given is not suitable");
        } catch (DukeException | IllegalStateException e) {
            ui.showError(e.getMessage());
        } catch (IllegalArgumentException e) {
            ui.showError(UI.getIndent() + "Invalid command: " + cmd);
        }
    }
}
