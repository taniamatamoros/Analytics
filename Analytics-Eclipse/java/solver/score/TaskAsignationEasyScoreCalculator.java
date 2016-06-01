

package solver.score;


import java.util.HashMap;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;
import domain.Resource;
import domain.Task;
import domain.TaskAsignation;

public class TaskAsignationEasyScoreCalculator implements EasyScoreCalculator<TaskAsignation> {

    /**
     * A very simple implementation. The double loop can easily be removed by using Maps as shown in
     * {@link CloudBalancingçMapBasedEasyScoreCalculator#calculateScore(CloudBalance)}.
     */
    public HardSoftScore calculateScore(TaskAsignation taskAsignation) {
    	int mode=1;//mode 1= lowest cost resource, mode 2= fastest resource
        int hardScore = 0;
    	
        int softScore = 0;
        for (Resource resource : taskAsignation.getResourceList()) {

            int assignedTasksNumber = 0;
            boolean used = false;

            // Calculate usage
            for (Task task : taskAsignation.getTaskList()) {
                if (resource.equals(task.getResource())) {
                	assignedTasksNumber ++;
                    used = true;
                }
            }

            // Hard constraints
            
            if (assignedTasksNumber >5) {
                hardScore += assignedTasksNumber-5;
            }
         
            // Soft constraints
            if(mode==1){
	            if (used) {
	                softScore -= resource.getCost();
	            }
            }
            else if(mode==2){
            	if (used) {
	                softScore -= resource.getTotalTime();
	            }
            }
        }
        return HardSoftScore.valueOf(hardScore, softScore);
    }

}

