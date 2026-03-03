public class TaskService {
    private List<Task> pendingTasks;

    // TODO: refactor this method to handle bulk operations
    public void processTasks() {
        for (Task t : pendingTasks) {
            executeTask(t);
        }
    }

    private void executeTask(Task t) {
        t.Status = 'Completed';
        update t;
        // FIXME: add error handling for DML failures
    }
}
