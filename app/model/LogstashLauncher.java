package model;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;


public class LogstashLauncher {
	
	public final static String logstashPath = "/home/dev/Serli/TutoLogStach/logstash-1.4.2";
	
	private final String jrubyhome = "/home/dev/logstash/lib/";
	private ScriptingContainer container;
	
	public LogstashLauncher() throws InterruptedException, FileNotFoundException {		
		/*initialization of a scripting container which
		 * enables to launch jruby files
		 */
        this.container = new ScriptingContainer();        
        List<String> loadPaths = new ArrayList();
        loadPaths.add(jrubyhome);
        this.container.setLoadPaths(loadPaths);
        this.container.setInput(System.in);
        this.container.setOutput(new PrintStream(logstashPath+"/test"));
    }
	
	/**
	 * Launches logstash with a bunch of parameters
	 * @param args
	 * @throws java.io.IOException
	 * @throws InterruptedException 
	 */
	public void launchLogstash(String pathToConfFile) throws IOException, InterruptedException{
		String[] args = new String[]{"agent","-e","input{stdin{}} "+pathToConfFile+" output{stdout{codec=>rubydebug}}"};
		this.container.setArgv(args);
		container.runScriptlet(PathType.ABSOLUTE,jrubyhome+"logstash/runner.rb");
	}
    
	public static void main(String[] args) throws InterruptedException, IOException{
		
			ConfigurationCleaner e = new ConfigurationCleaner();
			String confFilePath = e.createCleanConfigFile(logstashPath+"/logstash.logback3.conf");
			LogstashLauncher r = new LogstashLauncher();
			r.launchLogstash(confFilePath);
		
		
	}
}
