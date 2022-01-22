import java.util.*;

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
            try {
                switch (cmd) {
                    case BYE:
                        System.out.println(bye());
                        StillIn = false;
                        break;

                    case LIST:
                        list(list);
                        break;

                    case MARK:
                        if(input.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! I don't know what to mark");
                        }

                        int numMark = Integer.parseInt(input[1]);

                        if(numMark > list.size()) {
                            throw new DukeException("    ☹ OOPS!!! I don't see your task");
                        }

                        list.get(numMark - 1).setDone();
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("     " + list.get(numMark - 1));
                        break;

                    case UNMARK:
                        if(input.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! I don't know what to unmark");
                        }

                        int numUnmark = Integer.parseInt(input[1]);

                        if(numUnmark > list.size()) {
                            throw new DukeException("    ☹ OOPS!!! I don't see your task");
                        }

                        list.get(numUnmark - 1).setUndone();
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("     " + list.get(numUnmark - 1));
                        break;

                    case TODO:
                        if(input.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! The description of a todo cannot be empty");
                        }
                        Task todo = new ToDo(input[1]);
                        list.add(todo);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("     " + todo);
                        System.out.printf("    Now you have %d task(s) in the list\n", list.size());
                        break;

                    case DEADLINE:
                        if(input.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! The description of a deadline cannot be empty");
                        }
                        String[] splitbydate = input[1].split(" /by ");
                        if(splitbydate.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! I don't know when your task is due");
                        }
                        String bydate = splitbydate[1];
                        Task deadline = new Deadline(splitbydate[0], bydate);
                        list.add(deadline);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("     " + deadline);
                        System.out.printf("    Now you have %d task(s) in the list\n", list.size());
                        break;

                    case EVENT:
                        if(input.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! The description of an event cannot be empty");
                        }
                        String[] splitatdate = input[1].split(" /at ");
                        if(splitatdate.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! I don't know when your event happens");
                        }
                        String atdate = splitatdate[1];
                        Task event = new Event(splitatdate[0], atdate);
                        list.add(event);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("     " + event);
                        System.out.printf("    Now you have %d task(s) in the list\n", list.size());
                        break;

                    case DELETE:
                        if(input.length < 2) {
                            throw new DukeException("    ☹ OOPS!!! I don't know what to delete");
                        }

                        int removenum = Integer.parseInt(input[1]);

                        if(removenum > list.size()) {
                            throw new DukeException("    ☹ OOPS!!! I don't see your task");
                        }

                        Task removedtask = list.remove(removenum - 1);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("     " + removedtask);
                        System.out.printf("    Now you have %d task(s) in the list\n", list.size());
                }
            } catch(DukeException err) {
                System.out.println(err.getMessage());
            } catch(NumberFormatException err) {
                System.out.println("    ☹ OOPS!!! Task number given is not suitable");
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    public static void list(ArrayList<Task> lst) {
        for(int i=0; i < lst.size(); i++) {
            if(i == 0) {
                System.out.println("    Here are the tasks in your list:");
            }
            System.out.printf("     %d. %s\n", i + 1, lst.get(i));
        }
        if(lst.size() == 0) {
            System.out.println("    There's nothing in your list");
        }
    }

    public static String blah() {
        return "    blah";
    }

    public static String bye() {
        return "    Bye. Hope to see you again soon!";

    }
}