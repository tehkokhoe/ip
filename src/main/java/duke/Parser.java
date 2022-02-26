package duke;

import java.util.ArrayList;

public class Parser {
    /**
     * Returns a command by detecting the command in the user input.
     *
     * @param inputs user input that has been seperated into command and description.
     * @return the command from user input.
     * @throws IllegalArgumentException If command is not found in enum {@link Command}.
     * @see Command
     */
    public static Command parseCommand(String[] inputs) throws IllegalArgumentException {
        Command cmd = Command.valueOf(inputs[0].toUpperCase());
        return cmd;
    }

    /**
     * Returns index of list that should be marked.
     *
     * @param tasks the list of tasks saved.
     * @param inputs user input that has been seperated into command and description.
     * @return the index of the task in the list that should be marked.
     * @throws DukeException If index is not given, index given is not an integer or index > size
     * of task list.
     * @see Task
     */
    public static int parseMarkIndex(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know what to mark");
        }

        int markIndex = Integer.parseInt(inputs[1]);

        if (markIndex > tasks.size()) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't see your task");
        }

        return markIndex;
    }

    /**
     * Returns index of list that should be unmarked.
     *
     * @param tasks the list of tasks saved.
     * @param inputs user input that has been seperated into command and description.
     * @return the index of the task in the list that should be unmarked.
     * @throws DukeException If index is not given, index given is not an integer or index > size
     * of task list.
     * @see Task
     */
    public static int parseUnmarkIndex(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know what to mark");
        }

        int unmarkIndex = Integer.parseInt(inputs[1]);

        if (unmarkIndex > tasks.size()) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't see your task");
        }

        return unmarkIndex;
    }

    /**
     * Check if description of {@link ToDo} is empty.
     *
     * @param inputs user input that has been seperated into command and description.
     * @throws DukeException If input length < 2.
     */
    public static void checkToDoDescription(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! The description of a todo cannot be empty");
        }
    }

    /**
     * Check if description of {@link Deadline} is empty.
     *
     * @param inputs user input that has been seperated into command and description.
     * @throws DukeException If input length < 2.
     */
    public static void checkDeadlineDescription(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! The description of a deadline cannot be empty");
        }
    }

    /**
     * Returns a {@link String} array that separates the task description and the date.
     *
     * @param inputs user input that has been seperated into command and description.
     * @return the description of the task seperated into task description and the date.
     * @throws DukeException If date is not given or {@link Deadline} input is not in the right
     * format.
     */
    public static String[] splitDeadlineDate(String[] inputs) throws DukeException {
        String[] splitByDate = inputs[1].split(" /by ");

        if (splitByDate.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know when your task is due");
        }

        return splitByDate;
    }

    /**
     * Check if description of {@link Event} is empty.
     *
     * @param inputs user input that has been seperated into command and description.
     * @throws DukeException If input length < 2.
     */
    public static void checkEventDescription(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! The description of an event cannot be empty");
        }
    }

    /**
     * Returns a {@link String} array that separates the task description and the dates.
     *
     * @param inputs user input that has been seperated into command and description.
     * @return the description of the task seperated into task description and the dates.
     * @throws DukeException If dates are not given or {@link Event} input is not in the
     * right format.
     */
    public static String[] splitEventDate(String[] inputs) throws DukeException {
        String[] splitAtDate = inputs[1].split(" /at ");

        if (splitAtDate.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know when your event happens");
        }

        return splitAtDate;
    }

    /**
     * Returns the index of the list to delete.
     *
     * @param inputs user input that has been seperated into command and description.
     * @param tasks list of tasks saved.
     * @return the index of the task in the list to delete.
     * @throws DukeException If index is not given, index given is not an integer or index > size
     * of task list.
     */
    public static int parseDeleteIndex(String[] inputs, ArrayList<Task> tasks) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know what to delete");
        }

        int removeNum = Integer.parseInt(inputs[1]);

        if (removeNum > tasks.size()) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't see your task");
        }

        return removeNum;
    }

    /**
     * Returns a {@link String} array with the command and description seperated.
     *
     * @param ui the user interface that accepts user input.
     * @return the array with command and description seperated.
     */
    public static String[] parseInput(UI ui) {
        String in = ui.read();
        String[] inputs = in.split(" ", 2);
        return inputs;
    }

    /**
     * Returns a {@link String} array with the command and description seperated.
     *
     * @param input the user input.
     * @return the array with command and description seperated.
     */
    public static String[] parseGuiInput(String input) {
        String[] inputs = input.split(" ", 2);
        return inputs;
    }

    /**
     * Returns a Task object by parsing entry from saved file.
     *
     * @param entry a task string in the form saved in file.
     * @return the Task object that represents the entry inserted.
     */
    public static Task parseEntry(String entry) {
        String[] display = entry.split(" \\| ");
        Task task = new Task();

        if (display[0].equals("T")) {
            task = new ToDo(display[2]);
        } else if (display[0].equals("D")) {
            task = new Deadline(display[2], display[3]);
        } else if (display[0].equals("E")) {
            task = new Event(display[2], display[3]);
        }

        if (display[1].equals("1")) {
            task.setDone();
        }

        return task;
    }

    /**
     * Returns the keyword in the user input.
     *
     * @param input the user input.
     * @return the keyword associated with find command.
     * @throws DukeException If keyword is not given.
     */
    public static String parseKeyword(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know what keyword to look for");
        }

        return input[1];
    }
}
