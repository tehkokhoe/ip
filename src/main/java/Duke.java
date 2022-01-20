import java.util.*;

public class Duke {
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
        boolean StillIn = true;
        FastReader fr = new FastReader();
        ArrayList<Task> list = new ArrayList<Task>(100);
        while(StillIn) {
            String in = fr.nextLine();
            String[] input = in.split(" ");
            Task task = new Task(in);
            boolean printed = false;
            System.out.println("    ____________________________________________________________");
            if(input[0].equals("bye")) {
                System.out.println(bye());
                StillIn = false;
                printed = true;
            } else if(input[0].equals("list")) {
                list(list);
                printed = true;
            } else if(input[0].equals("mark")) {
                int num = Integer.parseInt(input[1]);
                list.get(num - 1).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + list.get(num - 1).getTask());
                printed = true;
            } else if(input[0].equals("unmark")) {
                int num = Integer.parseInt(input[1]);
                list.get(num - 1).setUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + list.get(num - 1).getTask());
            }

            if(printed) {
                System.out.println("    ____________________________________________________________");
                continue;
            }

            if(list.contains(task)) {
                System.out.println(task);
            } else {
                list.add(task);
                System.out.println("added: " + in);
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    public static void list(ArrayList<Task> lst) {
        for(int i=0; i < lst.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, lst.get(i));
        }
    }

    public static String blah() {
        return "    blah";
    }

    public static String bye() {
        return "    Bye. Hope to see you again soon!";

    }
}