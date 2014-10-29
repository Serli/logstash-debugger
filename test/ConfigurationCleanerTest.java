import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class ConfigurationCleanerTest {
	
	
	@Test
	public void methodShouldReturnPath(){
		ConfigurationCleaner testedClass = new ConfigurationCleaner();
		assertEquals("/home/dev/Serli/TutoLogStach/logstash-1.4.2/logstash.logback.conf", testedClass.createCleanConfigFile("/home/dev/workspace/logstashWeb/src/test/resources/linearConfFile.txt"));
		assertEquals("/home/dev/Serli/TutoLogStach/logstash-1.4.2/logstash.logback.conf", testedClass.createCleanConfigFile("/home/dev/workspace/logstashWeb/src/test/resources/classicConfFile.txt"));
		assertEquals("/home/dev/Serli/TutoLogStach/logstash-1.4.2/logstash.logback.conf", testedClass.createCleanConfigFile("/home/dev/workspace/logstashWeb/src/test/resources/multiInputOutputConfFile.txt"));
	}
}
