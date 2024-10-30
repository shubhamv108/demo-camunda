package code.shubham.demo.camuda.workers;

import io.camunda.zeebe.spring.client.annotation.JobWorker;
import io.camunda.zeebe.spring.client.annotation.Variable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessRestAPIHandlerWorker {

    @JobWorker(type = "handler")
    public Map<String, Object> commit(@Variable(name = "response") Map<String, Object> response) {
        System.out.println(String.format("handler response: %s", response));
        return response;
    }

}
