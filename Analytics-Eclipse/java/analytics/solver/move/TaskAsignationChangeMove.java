package analytics.solver.move;


import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

import analytics.domain.Resource;
import analytics.domain.Task;
import analytics.domain.TaskAsignation;

public class TaskAsignationChangeMove extends AbstractMove {

    private Task task;
    private Resource toResource;

    public TaskAsignationChangeMove(Task task, Resource toResource) {
        this.task = task;
        this.toResource = toResource;
    }

    //Es un movimiento posible si el recurso al que lo queremos asignar no es el que ya tiene asignado (no habría cambio)
    public boolean isMoveDoable(ScoreDirector scoreDirector) {
        return !ObjectUtils.equals(task.getResource(), toResource);
    }

    public Move createUndoMove(ScoreDirector scoreDirector) {
        return new TaskAsignationChangeMove(task, task.getResource());
    }

    @Override
    //Asigna la tarea a un nuevo recurso
    protected void doMoveOnGenuineVariables(ScoreDirector scoreDirector) {
    	TaskAsignationMoveHelper.moveResource(scoreDirector, task, toResource);
    }

    public Collection<? extends Object> getPlanningEntities() {
        return Collections.singletonList(task);
    }

    public Collection<? extends Object> getPlanningValues() {
        return Collections.singletonList(toResource);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof TaskAsignationChangeMove) {
        	TaskAsignationChangeMove other = (TaskAsignationChangeMove) o;
            return new EqualsBuilder()
                    .append(task, other.task)
                    .append(toResource, other.toResource)
                    .isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(task)
                .append(toResource)
                .toHashCode();
    }

    public String toString() {
        return task + " {" + task.getResource() + " -> " + toResource + "}";
    }

}