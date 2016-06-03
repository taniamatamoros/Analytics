package analytics.solver.move.factory;



import java.util.ArrayList;
import java.util.List;

import analytics.solver.move.TaskAsignationChangeMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.heuristic.selector.move.factory.MoveListFactory;

import analytics.domain.Resource;
import analytics.domain.Task;
import analytics.domain.TaskAsignation;


public class TaskAsignationChangeMoveFactory implements MoveListFactory<TaskAsignation> {

    public List<Move> createMoveList(TaskAsignation taskAsignation) {
        List<Move> moveList = new ArrayList<Move>();
        List<Resource> resourceList = taskAsignation.getResourceList();
        for (Task task : taskAsignation.getTaskList()) {
            for (Resource resource : resourceList) {
                moveList.add(new TaskAsignationChangeMove(task, resource));
            }
        }
        return moveList;
    }

}