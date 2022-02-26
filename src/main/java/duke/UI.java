package duke;

import java.util.ArrayList;

public class UI {
    private static final String[] dateFormat = {"d/MM/yyyy", "yyyy-MM-dd", "HHmm", "d/MM/yyyy HHmm", "yyyy-MM-dd HHmm"};
    private static final String INDENT = "    ";
    private static final String SPACE = " ";
    private FastReader fr;

    public UI() {
        fr = new FastReader();
    }

    /**
     * Prints the starting screen
     */
    public void startScreen() {
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
    }

    public String read() {
        return fr.nextLine();
    }

    public static void startLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    public static void endLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    public void byeDisplay() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }

    /**
     * Returns the list of tasks.
     *
     * @param tasks the list of tasks.
     * @return the response of list command.
     */
    public static String listDisplay(ArrayList<Task> tasks) {
        String response = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i == 0) {
                response += "Here are the tasks in your list:\n";
                System.out.println(INDENT + "Here are the tasks in your list:");
            }

            response += String.format(SPACE + "%d. %s\n", i + 1, tasks.get(i));
            System.out.printf(INDENT + SPACE + "%d. %s\n", i + 1, tasks.get(i));
        }

        if (tasks.size() == 0) {
            response += "There's nothing in your list\n";
            System.out.println(INDENT + "There's nothing in your list");
        }

        return response;
    }

    /**
     * Returns marked task.
     *
     * @param task the marked task.
     * @return the response to mark command.
     */
    public static String markDisplay(Task task) {
        String response = "";
        response += "Nice! I've marked this task as done:\n";
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        response += String.format(SPACE + "%s\n", task);
        System.out.println(INDENT + SPACE + task);
        return response;
    }

    /**
     * Returns unmarked task.
     *
     * @param task the unmarked task.
     * @return the response to unmark command.
     */
    public static String unmarkDisplay(Task task) {
        String response = "";
        response += "OK, I've marked this task as not done yet:\n";
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        response += String.format(SPACE + "%s\n", task);
        System.out.println(INDENT + SPACE + task);
        return response;
    }

    public static String getIndent() {
        return INDENT;
    }

    public static String getSpace() {
        return SPACE;
    }

    /**
     * Returns added {@link ToDo} {@link Task}.
     *
     * @param toDo  the added toDo task.
     * @param tasks the list of tasks.
     * @return the response to todo command.
     */
    public static String toDoDisplay(Task toDo, ArrayList<Task> tasks) {
        String response = "";
        response += "Got it. I've added this task:\n";
        System.out.println(INDENT + "Got it. I've added this task:");
        response += String.format(SPACE + "%s\n", toDo);
        System.out.println(INDENT + SPACE + toDo);
        response += String.format("Now you have %d task(s) in the list\n", tasks.size());
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
        return response;
    }

    /**
     * Returns added {@link Deadline} {@link Task}.
     *
     * @param deadline the added deadline task.
     * @param tasks    the list of tasks.
     * @return the response to deadline command.
     */
    public static String deadlineDisplay(Task deadline, ArrayList<Task> tasks) {
        String response = "";
        response += "Got it. I've added this task:\n";
        System.out.println(INDENT + "Got it. I've added this task:");
        response += String.format(SPACE + "%s\n", deadline);
        System.out.println(INDENT + SPACE + deadline);
        response += String.format("Now you have %d task(s) in the list\n", tasks.size());
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
        return response;
    }

    /**
     * Returns added {@link Event} {@link Task}.
     *
     * @param event the added event task.
     * @param tasks the list of tasks.
     * @return the response to event command.
     */
    public static String eventDisplay(Task event, ArrayList<Task> tasks) {
        String response = "";
        response += "Got it. I've added this task:\n";
        System.out.println(INDENT + "Got it. I've added this task:");
        response += String.format(SPACE + "%s\n", event);
        System.out.println(INDENT + SPACE + event);
        response += String.format("Now you have %d task(s) in the list\n", tasks.size());
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
        return response;
    }

    /**
     * Returns deleted task.
     *
     * @param deletedTask the deleted task.
     * @param tasks       the list of tasks.
     * @return the response to delete command.
     */
    public static String deleteDisplay(Task deletedTask, ArrayList<Task> tasks) {
        String response = "";
        response += "Noted. I've removed this task:\n";
        System.out.println(INDENT + "Noted. I've removed this task:");
        response += String.format(SPACE + "%s\n", deletedTask);
        System.out.println(INDENT + SPACE + deletedTask);
        response += String.format("Now you have %d task(s) in the list\n", tasks.size());
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
        return response;
    }

    /**
     * Returns loading error message.
     *
     * @param e the error message.
     * @return the loading error message.
     */
    public String showLoadingError(String e) {
        System.out.println(e);
        return e;
    }

    /**
     * Returns error message.
     *
     * @param e the error message.
     * @return the error message.
     */
    public String showError(String e) {
        System.out.println(e);
        return e;
    }

    /**
     * Returns a list of viable date formats for user input.
     *
     * @return the response for dateformat command.
     */
    public String showDateFormats() {
        String response = "";

        for (String s : dateFormat) {
            response += s + "\n";
            System.out.println(s);
        }

        return response;
    }

    /**
     * Return found tasks.
     *
     * @param found the list of found tasks containing keyword.
     * @return the response to find command.
     */
    public static String findDisplay(ArrayList<Task> found) {
        String response = "";
        if (found.size() < 1) {
            System.out.println(INDENT + "I couldn't find any task with that keyword");
            return "I couldn't find any task with that keyword";
        }

        response += "Here are the matching tasks in your list:\n";
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        int i = 1;

        for (Task task : found) {
            response += String.format(SPACE + "%d. %s\n", i, task);
            System.out.printf(INDENT + SPACE + "%d. %s\n", i, task);
            i += 1;
        }

        return response;
    }

    public static String duplicateDisplay(Task task) {
        return String.format("You already have task\n %s\non your list", task);
    }
}
