package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
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
     * Executes {@link Command} with inputs given and returns a response.
     *
     * @param cmd one of the commands given in the enum {@link Command}.
     * @param inputs the user input the has been split into command and description.
     * @return the response after executing command.
     * @see Command
     */
    public String execute(Command cmd, String[] inputs) {
        try {
            String result = "";
            switch (cmd) {
            case BYE:
                ui.byeDisplay();
                assert result == "";
                break;
            case LIST:
                result = tasks.list();
                assert result != "";
                break;
            case MARK:
                result = tasks.mark(inputs);
                storage.save(tasks.getTasks());
                assert result != "";
                break;
            case UNMARK:
                result = tasks.unmark(inputs);
                storage.save(tasks.getTasks());
                assert result != "";
                break;
            case TODO:
                result = tasks.addToDo(inputs);
                storage.save(tasks.getTasks());
                assert result != "";
                break;
            case DEADLINE:
                result = tasks.addDeadline(inputs);
                storage.save(tasks.getTasks());
                assert result != "";
                break;
            case EVENT:
                result = tasks.addEvent(inputs);
                storage.save(tasks.getTasks());
                assert result != "";
                break;
            case DELETE:
                result = tasks.delete(inputs);
                storage.save(tasks.getTasks());
                assert result != "";
                break;
            case DATEFORMAT:
                result = ui.showDateFormats();
                assert result != "";
                break;
            case FIND:
                result = tasks.find(inputs);
                assert result != "";
                break;
            default:
                throw new IllegalStateException(UI.getIndent() + "Unexpected value: " + cmd);
            }
            return result;
        } catch (NumberFormatException e) {
            return ui.showError(UI.getIndent() + "☹ OOPS!!! Task number given is not suitable");
        } catch (DukeException | IllegalStateException e) {
            return ui.showError(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ui.showError(UI.getIndent() + "Invalid command: " + cmd);
        }
    }

    public String getResponse(String input) {
        String[] inputs = Parser.parseGuiInput(input);
        try {
            Command cmd = Parser.parseCommand(inputs);
            assert cmd == Command.valueOf(inputs[0]);
            return this.execute(cmd, inputs);
        } catch (IllegalArgumentException e) {
            return ui.showError(UI.getIndent() + "Invalid Command: " + inputs[0]);
        }
    }
}
