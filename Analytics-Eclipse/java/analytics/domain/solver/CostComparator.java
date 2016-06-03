package analytics.domain.solver;

import org.apache.commons.lang3.builder.CompareToBuilder;

import analytics.domain.Resource;

import java.io.Serializable;
import java.util.Comparator;

public class CostComparator implements Comparator<Resource>, Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compare(Resource a, Resource b) {
	       return new CompareToBuilder()
	               .append(a.isAvailable(),b.isAvailable())
	               .append(b.getCost(),a.getCost()) // Descending (but this is debatable)
	               .toComparison();
	   }
	
}
