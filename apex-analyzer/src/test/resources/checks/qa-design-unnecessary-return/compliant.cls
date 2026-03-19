public class TaskProcessor {

    private List<Task> tasks;

    public TaskProcessor(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void processTasks() {
        for (Task t : tasks) {
            t.Status = 'Completed';
        }
        update tasks;
    }

    public String getStatus() {
        return 'Active';
    }

    public Integer getCount() {
        return tasks.size();
    }
}
