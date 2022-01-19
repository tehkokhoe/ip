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
        while(StillIn) {
            String input = fr.nextLine();
            System.out.println("    ____________________________________________________________");
            if(input.equals("list")) {
                System.out.println(list());
            } else if(input.equals("blah")) {
                System.out.println(blah());
            } else if(input.equals("bye")) {
                System.out.println(bye());
                StillIn = false;
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    public static String list() {
        return "    list";
    }

    public static String blah() {
        return "    blah";
    }

    public static String bye() {
        return "    Bye. Hope to see you again soon!";

    }
}
