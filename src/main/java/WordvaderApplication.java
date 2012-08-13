import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class WordvaderApplication extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		
		// Resources
		s.add(WordvaderResource.class);
		s.add(WordvaderReceiptResource.class);
		
		return s;
	}
}
