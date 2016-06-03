package analytics.domain;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.HashMap;

import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.common.swingui.components.Labeled;

@XStreamAlias("Resource")
public class Resource extends AbstractPersistable implements Labeled {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private double cost;
	private ArrayList<Task> assignedTasks=new ArrayList<Task> ();
	private HashMap<Integer,Double> efficiencyByTask=new HashMap<Integer,Double>();  //<TypeTask type, efficiency>

    public HashMap<Integer, Double> getEfficiencyByTask() {
        return efficiencyByTask;
    }
    
    public void setEfficiencyByTask(HashMap<Integer, Double> efficiencyByTask) {
        this.efficiencyByTask = efficiencyByTask;
    }

    public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
    


    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public Double getEfficiencyByTask(int type) {
        return efficiencyByTask.get(type);
    }
    public void setEfficiencyByTask(int type, Double time){
    	if(!efficiencyByTask.containsKey(type)){
    		efficiencyByTask.put(type, time);
    	}
    }
    
    public boolean isAvailable(){
    	boolean availability=false;
    	if(assignedTasks.size()<5){
    		availability=true;
    	}
    	 return availability;
    }
    public int getAssignedTasks() {
    	return this.assignedTasks.size();
    }
    
    public double getTotalTime(){
    	double totalTime=0;
    	for(Task task: assignedTasks){
    		totalTime+=task.getTotalTime();
    	}
    	return totalTime;
    }
    public void setTask(Task task){
    	assignedTasks.add(task);
    }
    
    public void removeTask(Task task){
    	assignedTasks.remove(task);
    }

    public String getLabel() {
        return "Resource " + id;
    }

}
