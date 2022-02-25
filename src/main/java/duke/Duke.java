package duke;

public class Duke {
    private UI ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an instance of the main program, by setting up the
     * {@link UI}, {@link Storage}, and loads the existing {@link TaskList}
     * or creates a new {@link TaskList} if one does not already exist.
     */
    public Duke() {
        ui = new UI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Displays start screen. Runs program and keeps the program running until
     * user exits. Accepts user input while running.
     */
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
        new Duke().run();
    }

    /**
     * Executes {@link Command} and inputs given.
     *
     * @param cmd one of the commands given in the enum {@link Command}
     * @param inputs the user input the has been split into command and description
     * @see Command
     */
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
            case FIND:
                tasks.find(inputs);
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
