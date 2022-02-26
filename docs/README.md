# User Guide

## Features 

### Adding a ToDo task: `todo`

Adds a ToDo task to the task list.

Format: `todo TASK_DESCRIPTION`

Example: `todo read book`

### Adding a Deadline task: `deadline`

Adds a Deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DATE`

Example: `deadline return book /by 10/02/2024 1100`

See [dateformat](https://github.com/tehkokhoe/ip/tree/master/docs#dateformat) for accepted formats for date input.

### Adding an Event task: `event`

Adds an Event task to the task list.

Format: `event TASK_DESCRIPTION /at START_DATE[-END_DATE]`

Example: 
- `event book fair /at 09/11/2022 1200-1300`
- `event lunch date /at 07/08/2022 1300`
- `event dinner date /at 04/12/2022`
  
See [dateformat](https://github.com/tehkokhoe/ip/tree/master/docs#dateformat) for accepted formats for date input.

### Viewing your task list: `list`

Displays your task list.

### Marking a task as done: `mark`

Marks a task as done in your task list.

format: `mark INDEX`

Example: `mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
 [T][X] read book
```

### Marking a task as not done: `unmark`

Marks a task as not done in your task list.

format: `unmark INDEX`

Example: `unmark 2`

Expected outcome:
```
OK, I've marked this task as not done yet:
 [T][ ] eat fish
```

### Deleting a task: `delete`

Deletes a task in your task list.

format: `delete INDEX`

Example: `delete 3`

Expected outcome:
```
Noted. I've removed this task:
 [T][ ] run
Now you have 2 task(s) in the list
```

### Finding tasks with specific keyword: `find`

Finds tasks that contains a keyword in the list.

format: `find KEYWORD`

Example: `find book`

Expected outcome:
```
Here are the matching tasks in your list:
 1. [T][X] read book
```

### Viewing accepted date formats: `dateformat`

Displays a list of available date formats for input.
