public class TaskService {
    private List<Task> pendingTasks;
    private Integer maxRetries = 3;

    public TaskService() {
        this.pendingTasks = new List<Task>();
    }

    public void processTasks() {
        for (Task t : pendingTasks) {
            executeTask(t);
        }
    }

    private void executeTask(Task t) {
        t.Status = 'Completed';
        update t;
    }
}
