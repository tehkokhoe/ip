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
        ArrayList<String> list = new ArrayList<String>(100);
        while(StillIn) {
            String input = fr.nextLine();
            System.out.println("    ____________________________________________________________");
            if(input.equals("bye")) {
                System.out.println(bye());
                StillIn = false;
                continue;
            } else if(input.equals("list")) {
                list(list);
                continue;
            }

            if(list.contains(input)) {
                System.out.println(input);
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    public static void list(ArrayList<String> lst) {
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
