package analytics.app;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import analytics.domain.*;
import persistence.TaskAsignationGenerator;

public class TaskAsignationHelloWorld {

    public static void main(String[] args) {
        // Build the Solver
        SolverFactory<TaskAsignation> solverFactory = SolverFactory.createFromXmlResource(
                "/solver/TaskAsignationSolverConfig.xml");
        Solver<TaskAsignation> solver = solverFactory.buildSolver();

        // Load a problem with 400 computers and 1200 processes
        TaskAsignation unsolvedTaskAsignation = new TaskAsignationGenerator().createTaskAsignation(10, 2);

        // Solve the problem
        TaskAsignation solvedTaskAsignation = solver.solve(unsolvedTaskAsignation);

        // Display the result
        System.out.println("\nSolved TaskAsignation with 10 tasks and 2 resources:\n"
                + toDisplayString(solvedTaskAsignation));
    }

    public static String toDisplayString(TaskAsignation taskAsignation) {
        StringBuilder displayString = new StringBuilder();
        for (Task task : taskAsignation.getTaskList()) {
            Resource resource = task.getResource();
            displayString.append("  ").append(task.getLabel()).append(" -> ")
                    .append(resource == null ? null : resource.getLabel()).append("\n");
        }
        return displayString.toString();
    }

}

