package petHealthApp;


import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

public class MyLoggingAdvisor  implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        System.out.println("----[LOG] Question: " + chatClientRequest.prompt().getUserMessage());
        ChatClientResponse response = callAdvisorChain.nextCall(chatClientRequest);
        System.out.println("----[LOG] Answer: " + response.chatResponse().getResult().getOutput().getText());
        return response;
    }

    @Override
    public String getName() {
        return "MyLoggingAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}