package code.shubham.demo.camuda.workers;

import org.springframework.stereotype.Component;

import java.util.Map;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class ProcessCommitWorker {

    @JobWorker(type = "commit")
    public Map<String, Double> commit(@Variable(name = "output") Double output) {
        System.out.println(String.format("commit output: %s", output));
        return Map.of("output", output);
    }

}
