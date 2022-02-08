import java.util.ArrayList;

public class Duke {
    private static final String INDENT = "    ";
    private static final String SPACE = " ";
    enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + "Hello! I'm Duke");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(INDENT + "____________________________________________________________");
        boolean isStillIn = true;
        FastReader fr = new FastReader();
        ArrayList<Task> tasks = new ArrayList<>(100);

        while (isStillIn) {
            String in = fr.nextLine();
            String[] inputs = in.split(" ", 2);
            Command cmd = Command.valueOf(inputs[0].toUpperCase());
            System.out.println(INDENT + "____________________________________________________________");

            try {
                switch (cmd) {
                case BYE:
                    System.out.println(bye());
                    isStillIn = false;
                    break;
                case LIST:
                    list(tasks);
                    break;
                case MARK:
                    mark(tasks, inputs);
                    break;
                case UNMARK:
                    unmark(tasks, inputs);
                    break;
                case TODO:
                    displayToDo(tasks, inputs);
                    break;
                case DEADLINE:
                    displayDeadline(tasks, inputs);
                    break;
                case EVENT:
                    displayEvent(tasks, inputs);
                    break;
                case DELETE:
                    delete(tasks, inputs);
                    break;
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            } catch (NumberFormatException err) {
                System.out.println(INDENT + "☹ OOPS!!! Task number given is not suitable");
            }

            System.out.println(INDENT + "____________________________________________________________");
        }
    }

    public static void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            if (i == 0) {
                System.out.println(INDENT + "Here are the tasks in your list:");
            }

            System.out.printf(INDENT + SPACE + "%d. %s\n", i + 1, tasks.get(i));
        }

        if (tasks.size() == 0) {
            System.out.println(INDENT + "There's nothing in your list");
        }
    }

    public static String blah() {
        return INDENT + "blah";
    }

    public static String bye() {
        return INDENT + "Bye. Hope to see you again soon!";
    }

    public static void mark(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't know what to mark");
        }

        int numMark = Integer.parseInt(inputs[1]);

        if (numMark > tasks.size()) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't see your task");
        }

        tasks.get(numMark - 1).setDone();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + SPACE + tasks.get(numMark - 1));
    }

    public static void unmark(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't know what to unmark");
        }

        int numUnmark = Integer.parseInt(inputs[1]);

        if (numUnmark > tasks.size()) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't see your task");
        }

        tasks.get(numUnmark - 1).setUndone();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + SPACE + tasks.get(numUnmark - 1));
    }

    public static void displayToDo(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! The description of a todo cannot be empty");
        }

        Task toDo = new ToDo(inputs[1]);
        tasks.add(toDo);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + SPACE + toDo);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }

    public static void displayDeadline(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! The description of a deadline cannot be empty");
        }

        String[] splitByDate = inputs[1].split(" /by ");

        if (splitByDate.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't know when your task is due");
        }

        String byDate = splitByDate[1];
        Task deadline = new Deadline(splitByDate[0], byDate);
        tasks.add(deadline);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + SPACE + deadline);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }

    public static void displayEvent(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! The description of an event cannot be empty");
        }

        String[] splitAtDate = inputs[1].split(" /at ");

        if (splitAtDate.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't know when your event happens");
        }

        String atDate = splitAtDate[1];
        Task event = new Event(splitAtDate[0], atDate);
        tasks.add(event);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + SPACE + event);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }

    public static void delete(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't know what to delete");
        }

        int removeNum = Integer.parseInt(inputs[1]);

        if (removeNum > tasks.size()) {
            throw new DukeException(INDENT + "☹ OOPS!!! I don't see your task");
        }

        Task removedTask = tasks.remove(removeNum - 1);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + SPACE + removedTask);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }
}