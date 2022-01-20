import java.util.*;

public class Duke {
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT
        ;
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
        boolean StillIn = true;
        FastReader fr = new FastReader();
        ArrayList<Task> list = new ArrayList<Task>(100);
        while(StillIn) {
            String in = fr.nextLine();
            String[] input = in.split(" ", 2);
            Command cmd = Command.valueOf(input[0].toUpperCase());
            System.out.println("    ____________________________________________________________");
            switch(cmd) {
                case BYE:
                    System.out.println(bye());
                    StillIn = false;
                    break;

                case LIST:
                    list(list);
                    break;

                case MARK:
                    int numMark = Integer.parseInt(input[1]);
                    list.get(numMark - 1).setDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println(list.get(numMark - 1));
                    break;

                case UNMARK:
                    int numUnmark = Integer.parseInt(input[1]);
                    list.get(numUnmark - 1).setUndone();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println(list.get(numUnmark - 1));
                    break;

                case TODO:
                    Task todo = new ToDo(input[1]);
                    list.add(todo);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.printf("    Now you have %d task(s) in the list\n", list.size());
                    break;

                case DEADLINE:
                    String bydate = input[1].split("/by ")[1];
                    Task deadline = new Deadline(input[1], bydate);
                    list.add(deadline);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.printf("    Now you have %d task(s) in the list\n", list.size());
                    break;

                case EVENT:
                    String atdate = input[1].split("/at ")[1];
                    Task event = new Event(input[1], atdate);
                    list.add(event);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println(event);
                    System.out.printf("    Now you have %d task(s) in the list\n", list.size());
                    break;
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    public static void list(ArrayList<Task> lst) {
        for(int i=0; i < lst.size(); i++) {
            System.out.printf("    %d. %s\n", i + 1, lst.get(i));
        }
    }

    public static String blah() {
        return "    blah";
    }

    public static String bye() {
        return "    Bye. Hope to see you again soon!";

    }
}