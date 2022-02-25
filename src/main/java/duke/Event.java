package duke;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

public class Event extends Task {
    private String atString;
    private DateTime[] atDate;

    /**
     * Constructs a {@link Task} that have dates associated to it. The date
     * associated is normally the date that the {@link Task} occurs and ends.
     *
     * @param task the description of the task.
     * @param atString the start date and end date of the task.
     */
    public Event(String task, String atString) {
        super(task);
        this.atString = atString;
        String[] arr = atString.split("\\s*-\\s*", 2);

        try {
            DateTimeParser[] dateParsers = {
                    DateTimeFormat.forPattern("d/MM/yyyy").getParser(),
                    DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
                    DateTimeFormat.forPattern("HHmm").getParser(),
                    DateTimeFormat.forPattern("d/MM/yyyy HHmm").getParser(),
                    DateTimeFormat.forPattern("yyyy-MM-dd HHmm").getParser(),
            };
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(null, dateParsers).toFormatter();
            atDate = new DateTime[2];

            for (int i = 0; i < 2; i++) {
                DateTime date = formatter.parseDateTime(arr[i]);
                this.atDate[i] = date;
            }
        } catch (UnsupportedOperationException e) {
            this.atDate[0] = null;
            this.atDate[1] = null;
        } catch (IllegalArgumentException e) {
            this.atDate[0] = null;
            this.atDate[1] = null;
        }
    }

    @Override
    public String toString() {
        String formattedDate = "";
        if (this.atDate[0] == null && this.atDate[1] == null) {
            return "[E]" + super.toString() + " (at: " + atString + ")";
        } else if (this.atDate[0] != null && this.atDate[1] != null) {
            if (this.atDate[1].isAfter(this.atDate[0])) {
                formattedDate = DateTimeFormat.forPattern("MMM dd yyyy h:mm a").print(atDate[0])
                        + " - " + DateTimeFormat.forPattern("MMM dd yyyy h:mm a").print(atDate[1]);
            } else {
                formattedDate = DateTimeFormat.forPattern("MMM dd yyyy h:mm a").print(atDate[0])
                        + " - " + DateTimeFormat.forPattern("h:mm a").print(atDate[1]);
            }
        } else if (this.atDate[0] == null) {
            String[] arr = atString.split("\\s+-\\s+", 2);
            formattedDate = arr[0] + "-"
                    + DateTimeFormat.forPattern("MMM dd yyyy h:mm a").print(atDate[1]);
        } else {
            String[] arr = atString.split("\\s+-\\s+", 2);
            formattedDate = DateTimeFormat.forPattern("MMM dd yyyy h:mm a").print(atDate[0])
                    + "-" + arr[1];
        }
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    @Override
    public String toRecord() {
        return "E | " + super.toRecord() + " | " + atString;
    }
}
