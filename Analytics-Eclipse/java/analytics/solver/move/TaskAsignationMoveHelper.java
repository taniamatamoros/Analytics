package analytics.solver.move;

import org.optaplanner.core.impl.score.director.ScoreDirector;

import analytics.domain.Resource;
import analytics.domain.Task;

public class TaskAsignationMoveHelper {
	
    public static void moveResource(ScoreDirector scoreDirector, Task task,
    		Resource toResource) {
        scoreDirector.beforeVariableChanged(task, "resource");
        task.setResource(toResource);
        scoreDirector.afterVariableChanged(task, "resource");
    }

    private TaskAsignationMoveHelper() {
    }

}
