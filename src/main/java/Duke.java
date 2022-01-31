import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

public class Duke {
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
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        boolean isStillIn = true;
        FastReader fr = new FastReader();
        ArrayList<Task> tasks = new ArrayList<>(100);

        while (isStillIn) {
            String in = fr.nextLine();
            String[] inputs = in.split(" ", 2);
            Command cmd = Command.valueOf(inputs[0].toUpperCase());
            System.out.println("    ____________________________________________________________");

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
                    save(tasks);
                    break;
                case UNMARK:
                    unmark(tasks, inputs);
                    save(tasks);
                    break;
                case TODO:
                    displayToDo(tasks, inputs);
                    save(tasks);
                    break;
                case DEADLINE:
                    displayDeadline(tasks, inputs);
                    save(tasks);
                    break;
                case EVENT:
                    displayEvent(tasks, inputs);
                    save(tasks);
                    break;
                case DELETE:
                    delete(tasks, inputs);
                    save(tasks);
                    break;
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            } catch (NumberFormatException err) {
                System.out.println("    ☹ OOPS!!! Task number given is not suitable");
            }

            System.out.println("    ____________________________________________________________");
        }
    }

    public static void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            if (i == 0) {
                System.out.println("    Here are the tasks in your list:");
            }

            System.out.printf("     %d. %s\n", i + 1, tasks.get(i));
        }

        if (tasks.size() == 0) {
            System.out.println("    There's nothing in your list");
        }
    }

    public static String blah() {
        return "    blah";
    }

    public static String bye() {
        return "    Bye. Hope to see you again soon!";
    }

    public static void mark(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("    ☹ OOPS!!! I don't know what to mark");
        }

        int numMark = Integer.parseInt(inputs[1]);

        if (numMark > tasks.size()) {
            throw new DukeException("    ☹ OOPS!!! I don't see your task");
        }

        tasks.get(numMark - 1).setDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("     " + tasks.get(numMark - 1));
    }

    public static void unmark(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("    ☹ OOPS!!! I don't know what to unmark");
        }

        int numUnmark = Integer.parseInt(inputs[1]);

        if (numUnmark > tasks.size()) {
            throw new DukeException("    ☹ OOPS!!! I don't see your task");
        }

        tasks.get(numUnmark - 1).setUndone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("     " + tasks.get(numUnmark - 1));
    }

    public static void displayToDo(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("    ☹ OOPS!!! The description of a todo cannot be empty");
        }

        Task toDo = new ToDo(inputs[1]);
        tasks.add(toDo);
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + toDo);
        System.out.printf("    Now you have %d task(s) in the list\n", tasks.size());
    }

    public static void displayDeadline(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("    ☹ OOPS!!! The description of a deadline cannot be empty");
        }

        String[] splitByDate = inputs[1].split(" /by ");

        if (splitByDate.length < 2) {
            throw new DukeException("    ☹ OOPS!!! I don't know when your task is due");
        }

        String byDate = splitByDate[1];
        Task deadline = new Deadline(splitByDate[0], byDate);
        tasks.add(deadline);
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + deadline);
        System.out.printf("    Now you have %d task(s) in the list\n", tasks.size());
    }

    public static void displayEvent(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("    ☹ OOPS!!! The description of an event cannot be empty");
        }

        String[] splitAtDate = inputs[1].split(" /at ");

        if (splitAtDate.length < 2) {
            throw new DukeException("    ☹ OOPS!!! I don't know when your event happens");
        }

        String atDate = splitAtDate[1];
        Task event = new Event(splitAtDate[0], atDate);
        tasks.add(event);
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + event);
        System.out.printf("    Now you have %d task(s) in the list\n", tasks.size());
    }

    public static void delete(ArrayList<Task> tasks, String[] inputs) throws DukeException {
        if (inputs.length < 2) {
            throw new DukeException("    ☹ OOPS!!! I don't know what to delete");
        }

        int removeNum = Integer.parseInt(inputs[1]);

        if (removeNum > tasks.size()) {
            throw new DukeException("    ☹ OOPS!!! I don't see your task");
        }

        Task removedTask = tasks.remove(removeNum - 1);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("     " + removedTask);
        System.out.printf("    Now you have %d task(s) in the list\n", tasks.size());
    }

    public static void save(ArrayList<Task> tasks) {
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
                output.append(tasks.get(0).toRecord()).append("\n");
            }

            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}