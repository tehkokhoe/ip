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
     * Prints the list of tasks.
     */
    public static void listDisplay(ArrayList<Task> tasks) {
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

    /**
     * Print marked task.
     *
     * @param task the marked task.
     */
    public static void markDisplay(Task task) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + SPACE + task);
    }

    /**
     * Print unmarked task.
     *
     * @param task the unmarked task.
     */
    public static void unmarkDisplay(Task task) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + SPACE + task);
    }

    public static String getIndent() {
        return INDENT;
    }

    public static String getSpace() {
        return SPACE;
    }

    /**
     * Print added {@link ToDo} {@link Task}.
     *
     * @param toDo the added toDo task.
     * @param tasks the list of tasks.
     */
    public static void toDoDisplay(Task toDo, ArrayList<Task> tasks) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + SPACE + toDo);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }

    /**
     * Print added {@link Deadline} {@link Task}.
     *
     * @param deadline the added deadline task.
     * @param tasks the list of tasks.
     */
    public static void deadlineDisplay(Task deadline, ArrayList<Task> tasks) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + SPACE + deadline);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }

    /**
     * Print added {@link Event} {@link Task}.
     *
     * @param event the added event task.
     * @param tasks the list of tasks.
     */
    public static void eventDisplay(Task event, ArrayList<Task> tasks) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + SPACE + event);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }

    /**
     * Print deleted task.
     *
     * @param deletedTask the deleted task.
     * @param tasks the list of tasks.
     */
    public static void deleteDisplay(Task deletedTask, ArrayList<Task> tasks) {
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + SPACE + deletedTask);
        System.out.printf(INDENT + "Now you have %d task(s) in the list\n", tasks.size());
    }

    public void showLoadingError(String e) {
        System.out.println(e);
    }

    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Print a list of viable date formats for user input.
     */
    public void showDateFormats() {
        for (String s : dateFormat) {
            System.out.println(s);
        }
    }

    public static void findDisplay(ArrayList<Task> found) {
        if (found.size() < 1) {
            System.out.println(INDENT + "I couldn't find any task with that keyword");
            return;
        }

        System.out.println(INDENT + "Here are the matching tasks in your list:");
        int i = 1;

        for (Task task : found) {
            System.out.printf(INDENT + SPACE + "%d. %s\n", i, task);
            i += 1;
        }
    }
}
