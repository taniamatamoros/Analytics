package domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.examples.cloudbalancing.domain.solver.CloudComputerStrengthComparator;
import org.optaplanner.examples.cloudbalancing.domain.solver.CloudProcessDifficultyComparator;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@PlanningEntity(difficultyComparatorClass = CloudProcessDifficultyComparator.class)
@XStreamAlias("Task")
public class Task extends AbstractPersistable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int requestorID;
	private TaskType type;     
    private boolean prioritary; 
    private boolean initiated;
    private Date generationTime; 
    
    // Planning variables: changes during planning, between score calculations.
    private Resource resource;

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public int getRequestorID() {
        return requestorID;
    }

    public void setRequestorID(int requestorID) {
        this.requestorID = requestorID;
    }

    public boolean isPrioritary() {
		return prioritary;
	}

	public void setPrioritary(boolean prioritary) {
		this.prioritary = prioritary;
	}

	public Date getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(Date generationTime) {
        this.generationTime = generationTime;
    }

    public boolean isInitiated() {
		return initiated;
	}

	public void setInitiated(boolean initiated) {
		this.initiated = initiated;
	}

	@PlanningVariable(valueRangeProviderRefs = {"resourceRange"},
            strengthComparatorClass = CloudComputerStrengthComparator.class)
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public Double getTotalTime() {
        return type.getaverageTimeForConclusion()*resource.getEfficiencyByTask(type.getType());
    }

    public String getLabel() {
        return "Task " + id + " type "+type.toString();
    }

}
