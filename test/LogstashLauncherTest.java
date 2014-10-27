import static org.junit.Assert.*;

import org.junit.Test;

public class LogstashLauncherTest {
	
	private static LogstashLauncher launcher;
	
	@Test
	public void constructorShouldNotThrowException(){
		try {
			launcher = new LogstashLauncher();
		} catch (Exception e){
			fail("Exception levée");
		}
	}
	
	@Test
	public void launcherShouldNotThrowException(){
		try {
			launcher = new LogstashLauncher();
			launcher.launchLogstash("/home/dev/Serli/TutoLogStach/logstash-1.4.2/logstash-logback.conf");
		} catch (Exception e){
			e.printStackTrace();
			fail("Exception levée");
		}
	}
	
	@Test
	public void launcherShouldThrowException(){
		try {
			//launcher = new LogstashLauncher();
			launcher.launchLogstash("");
			fail("Pas d'exception levée");
		} catch (Exception e){
		}
	}

}
