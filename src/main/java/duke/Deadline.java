package duke;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public class Deadline extends Task {
    private String byString;
    private DateTime byDate;

    /**
     * Constructs a {@link Task} that has a date associated to it. The date
     * associated is normally the date that the {@link Task} should be finished.
     *
     * @param task the description of the task.
     * @param byString the due date of the task.
     */
    public Deadline(String task, String byString) {
        super(task);
        this.byString = byString;

        try {
            DateTimeParser[] dateParsers = {
                    DateTimeFormat.forPattern("d/MM/yyyy").getParser(),
                    DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
                    DateTimeFormat.forPattern("HHmm").getParser(),
                    DateTimeFormat.forPattern("d/MM/yyyy HHmm").getParser(),
                    DateTimeFormat.forPattern("yyyy-MM-dd HHmm").getParser(),
            };
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(null, dateParsers).toFormatter();
            DateTime date = formatter.parseDateTime(byString);
            this.byDate = date;
        } catch (UnsupportedOperationException e) {
            this.byDate = null;
        } catch (IllegalArgumentException e) {
            this.byDate = null;
        }
    }

    @Override
    public String toString() {
        if (this.byDate == null) {
            return "[D]" + super.toString() + " (by: " + byString + ")";
        } else {
            String formattedDate = DateTimeFormat.forPattern("MMM dd yyyy h:mm a").print(byDate);
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        }
    }

    @Override
    public String toRecord() {
        return "D | " + super.toRecord() + " | " + byString;
    }
}
