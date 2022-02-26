package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> tasks;
    private ArrayList<Task> display;

    /**
     * Constructs a list of tasks and converts entries in save file to a list of {@link Task}
     * objects.
     *
     * @param tasks list of tasks saved.
     */
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

    public String list() {
        return UI.listDisplay(display);
    }

    /**
     * Marks a task, and returns a response.
     *
     * @param inputs the user input that has been seperated into command and index.
     * @return the response after marking a task.
     * @throws DukeException If index is not given, index given is not an integer or
     * index > size of task list.
     */
    public String mark(String[] inputs) throws DukeException {
        int markIndex = Parser.parseMarkIndex(display, inputs);
        display.get(markIndex - 1).setDone();
        tasks.set(markIndex - 1, display.get(markIndex - 1).toRecord());
        return UI.markDisplay(display.get(markIndex - 1));
    }

    /**
     * Unmarks a task and returns a response.
     *
     * @param inputs the user input that has been seperated into command and index.
     * @return the response after unmarking a task.
     * @throws DukeException If index is not given, index given is not an integer or
     * index > size of task list.
     */
    public String unmark(String[] inputs) throws DukeException {
        int unmarkIndex = Parser.parseUnmarkIndex(display, inputs);
        display.get(unmarkIndex - 1).setUndone();
        tasks.set(unmarkIndex - 1, display.get(unmarkIndex - 1).toRecord());
        return UI.unmarkDisplay(display.get(unmarkIndex - 1));
    }

    /**
     * Adds a {@link ToDo} {@link Task} and returns a response.
     *
     * @param inputs the user input that has been seperated into command and description.
     * @return the response after adding the to do task to the list.
     * @throws DukeException If input length < 2.
     */
    public String addToDo(String[] inputs) throws DukeException {
        Parser.checkToDoDescription(inputs);
        Task toDo = new ToDo(inputs[1]);
        if (this.isDuplicate(toDo)) {
            return UI.duplicateDisplay(toDo);
        }
        display.add(toDo);
        tasks.add(toDo.toRecord());
        return UI.toDoDisplay(toDo, display);
    }

    /**
     * Adds a {@link Deadline} {@link Task} and returns a response.
     *
     * @param inputs the user input that has been seperated into command and description.
     * @return the response after adding the deadline task to the list.
     * @throws DukeException If input length < 2.
     */
    public String addDeadline(String[] inputs) throws DukeException {
        Parser.checkDeadlineDescription(inputs);
        String[] splitByDate = Parser.splitDeadlineDate(inputs);
        String byDate = splitByDate[1];
        Task deadline = new Deadline(splitByDate[0], byDate);
        if (this.isDuplicate(deadline)) {
            return UI.duplicateDisplay(deadline);
        }
        display.add(deadline);
        tasks.add(deadline.toRecord());
        return UI.deadlineDisplay(deadline, display);
    }

    /**
     * Adds a {@link Event} {@link Task} and returns a response.
     *
     * @param inputs user input that has been seperated into command and description.
     * @return the response after adding the event task to the list.
     * @throws DukeException If input length < 2.
     */
    public String addEvent(String[] inputs) throws DukeException {
        Parser.checkEventDescription(inputs);
        String[] splitAtDate = Parser.splitEventDate(inputs);
        String atDate = splitAtDate[1];
        Task event = new Event(splitAtDate[0], atDate);
        if (this.isDuplicate(event)) {
            return UI.duplicateDisplay(event);
        }
        display.add(event);
        tasks.add(event.toRecord());
        return UI.eventDisplay(event, display);
    }

    /**
     * Delete a task from the task list and returns a response.
     *
     * @param inputs user input that has been seperated into command and index.
     * @return the response after deleting a task from the list.
     * @throws DukeException If index is not given, index given is not an integer or
     * index > size of task list.
     */
    public String delete(String[] inputs) throws DukeException {
        int deleteIndex = Parser.parseDeleteIndex(inputs, display);
        Task deletedTask = display.remove(deleteIndex - 1);
        tasks.remove(deleteIndex - 1);
        return UI.deleteDisplay(deletedTask, display);
    }

    /**
     * Find a task from the task list and return a response
     *
     * @param inputs user input that has been seperated into command and keyword.
     * @return the response after finding a task from the list.
     * @throws DukeException If keyword is not given.
     */
    public String find(String[] inputs) throws DukeException {
        String keyword = Parser.parseKeyword(inputs);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(keyword)) {
                foundTasks.add(display.get(i));
            }
        }
        return UI.findDisplay(foundTasks);
    }

    public boolean isDuplicate(Task task) {
        return display.contains(task);
    }
}
