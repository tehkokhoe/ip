package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> tasks;
    private ArrayList<Task> display;
    public TaskList(ArrayList<String> tasks) {
        this.tasks = tasks;
        this.display = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            display.add(Parser.parseEntry(tasks.get(i)));
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<String>();
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void list() {
        UI.listDisplay(display);
    }

    public void mark(String[] inputs) throws DukeException {
        int markIndex = Parser.parseMarkIndex(display, inputs);
        display.get(markIndex - 1).setDone();
        tasks.set(markIndex - 1, display.get(markIndex - 1).toRecord());
        UI.markDisplay(display.get(markIndex - 1));
    }

    public void unmark(String[] inputs) throws DukeException {
        int unmarkIndex = Parser.parseUnmarkIndex(display, inputs);
        display.get(unmarkIndex - 1).setUndone();
        tasks.set(unmarkIndex - 1, display.get(unmarkIndex - 1).toRecord());
        UI.unmarkDisplay(display.get(unmarkIndex - 1));
    }

    public void addToDo(String[] inputs) throws DukeException {
        Parser.checkToDoDescription(inputs);
        Task toDo = new ToDo(inputs[1]);
        display.add(toDo);
        tasks.add(toDo.toRecord());
        UI.toDoDisplay(toDo, display);
    }

    public void addDeadline(String[] inputs) throws DukeException {
        Parser.checkDeadlineDescription(inputs);
        String[] splitByDate = Parser.splitDeadlineDate(inputs);
        String byDate = splitByDate[1];
        Task deadline = new Deadline(splitByDate[0], byDate);
        display.add(deadline);
        tasks.add(deadline.toRecord());
        UI.deadlineDisplay(deadline, display);
    }

    public void addEvent(String[] inputs) throws DukeException {
        Parser.checkEventDescription(inputs);
        String[] splitAtDate = Parser.splitEventDate(inputs);
        String atDate = splitAtDate[1];
        Task event = new Event(splitAtDate[0], atDate);
        display.add(event);
        tasks.add(event.toRecord());
        UI.eventDisplay(event, display);
    }

    public void delete(String[] inputs) throws DukeException {
        int deleteIndex = Parser.parseDeleteIndex(inputs, display);
        Task deletedTask = display.remove(deleteIndex - 1);
        tasks.remove(deleteIndex - 1);
        UI.deleteDisplay(deletedTask, display);
    }
}
