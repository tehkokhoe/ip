public class Task {
    private String task;
    private boolean isDone;

    public Task() {
        this.isDone = false;
    }

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
        if (other instanceof Task){
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
