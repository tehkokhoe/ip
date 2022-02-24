import java.util.ArrayList;

public class Parser {
    public static Command parseCommand(String[] inputs) {
        Command cmd = Command.valueOf(inputs[0].toUpperCase());
        return cmd;
    }

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

    public static void checkToDoDescription(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! The description of a todo cannot be empty");
        }
    }

    public static void checkDeadlineDescription(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! The description of a deadline cannot be empty");
        }
    }

    public static String[] splitDeadlineDate(String[] inputs) throws DukeException {
        String[] splitByDate = inputs[1].split(" /by ");

        if (splitByDate.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know when your task is due");
        }

        return splitByDate;
    }

    public static void checkEventDescription(String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! The description of an event cannot be empty");
        }
    }

    public static String[] splitEventDate(String[] inputs) throws DukeException {
        String[] splitAtDate = inputs[1].split(" /at ");

        if (splitAtDate.length < 2) {
            throw new DukeException(UI.getIndent() + "☹ OOPS!!! I don't know when your event happens");
        }

        return splitAtDate;
    }

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

    public static String[] parseInput(UI ui) {
        String in = ui.read();
        String[] inputs = in.split(" ", 2);
        return inputs;
    }

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
}
