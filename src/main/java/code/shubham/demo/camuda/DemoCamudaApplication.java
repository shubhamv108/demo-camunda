package code.shubham.demo.camuda;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
@Deployment(resources = { "classpath:process.bpmn", "classpath:process-restapi.bpmn" })
public class DemoCamudaApplication implements CommandLineRunner {

	@Autowired
	private ZeebeClient zeebeClient;

	public static void main(String[] args) {
		SpringApplication.run(DemoCamudaApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		startProcessInstanceAndGetKey("process", Map.of("input", 100));
		startProcessInstanceAndGetKey("process-restapi", Map.of());
	}

	private long startProcessInstanceAndGetKey(String processId, Map<String, Object> variables) {
		var event = this.zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId(processId)
				.latestVersion()
				.variables(variables)
				.send()
				.join();
		System.out.println(String.format("started a process instance: %s", event.getProcessInstanceKey()));
		return event.getProcessInstanceKey();
	}
}
