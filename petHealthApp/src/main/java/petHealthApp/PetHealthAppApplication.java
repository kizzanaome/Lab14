package petHealthApp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetHealthAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetHealthAppApplication.class, args);
	}

	@Bean
	public ChatClient chatClient(ChatModel chatModel, ChatMemory chatMemory) {
		ChatClient.Builder builder = ChatClient.builder(chatModel);

		builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build());
		builder.defaultAdvisors(new MyLoggingAdvisor());
		return builder.build();
	}

}


