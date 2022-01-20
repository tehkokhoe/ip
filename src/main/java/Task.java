public class Task {
    private String task;
    private boolean done;

    public Task() {
        this.done = false;
    }

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return task;
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone() {
        this.done = false;
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Task){
            Task toCompare = (Task) other;
            return this.task.equals(toCompare.getTask());
        }
        return false;
    }

    @Override
    public String toString() {
        String temp;
        if(this.done) {
            temp = "X";
        } else {
            temp = " ";
        }
        return "[" + temp + "] " + this.task;
    }
}
