package duke;

public enum Command {
    BYE {
        public boolean isRunning() {
            return false;
        }
    },
    LIST {
        public boolean isRunning() {
            return true;
        }
    },
    MARK {
        public boolean isRunning() {
            return true;
        }
    },
    UNMARK {
        public boolean isRunning() {
            return true;
        }
    },
    TODO {
        public boolean isRunning() {
            return true;
        }
    },
    DEADLINE {
        public boolean isRunning() {
            return true;
        }
    },
    EVENT {
        public boolean isRunning() {
            return true;
        }
    },
    DELETE {
        public boolean isRunning() {
            return true;
        }
    },
    DATEFORMAT {
        public boolean isRunning() {
            return true;
        }
    },
    FIND {
        public boolean isRunning() {
            return true;
        }
    };

    public abstract boolean isRunning();
}


