package duke;

public class Task {
    private String task;
    private boolean isDone;

    public Task() {
        this.isDone = false;
    }

    /**
     * Constructs a task.
     * <p>
     * Task is initialized as not done.
     *
     * @param task the task description.
     * @return the index of the task in the list to delete.
     * @throws DukeException If index is not given, index given is not an integer or index > size
     * of task list.
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Task) {
            Task toCompare = (Task) other;
            return this.task.equals(toCompare.getTask());
        }
        return false;
    }

    @Override
    public String toString() {
        String temp;
        if (this.isDone) {
            temp = "X";
        } else {
            temp = " ";
        }
        return "[" + temp + "] " + this.task;
    }

    /**
     * Returns a {@link String} representing a {@link Task} in the form convenient for record.
     *
     * @return the text representing the task to record in save file.
     */
    public String toRecord() {
        int temp;

        if (this.isDone) {
            temp = 1;
        } else {
            temp = 0;
        }

        return temp + " | " + this.task;
    }
}
