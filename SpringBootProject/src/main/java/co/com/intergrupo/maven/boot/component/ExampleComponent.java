package co.com.intergrupo.maven.boot.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("exampleComponent")
public class ExampleComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleComponent.class); 
	
	public void llamandoUnMetodoDeUnComponente() {
		LOGGER.info("SE HA LLAMDO AL METODO SAYHELLO DE EXAMPLECOMPONENT");
	}
	
}
