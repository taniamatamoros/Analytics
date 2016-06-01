package domain.solver;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import domain.Task;

public class EfficiencyComparator implements Comparator<Task>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compare(Task a, Task b) {
        return new CompareToBuilder()
                .append(a.isPrioritary(),b.isPrioritary())
                .append(b.getTotalTime(),a.getTotalTime()) // Descending (but this is debatable)
                .toComparison();
    }

}
