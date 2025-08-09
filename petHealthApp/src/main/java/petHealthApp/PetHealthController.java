package petHealthApp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pet-health")
public class PetHealthController {

    private final ChatClient chatClient;

    private final List<Map<String, String>> history = new ArrayList<>();

    public PetHealthController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * GET /api/pet-health/ask?question=Your+text+here
     *
     * @RequestParam String question  -> reads the "question" from the URL query string
     * Response: JSON object with "question" and "answer"
     */
    @GetMapping("/ask")
    public ResponseEntity<Map<String, String>> ask(@RequestParam String question) {
        // 1) Validate input
        if (question == null || question.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Query parameter 'question' is required and cannot be empty.");
            return ResponseEntity.badRequest().body(error);
        }

        // 2) Call the model (assuming you have a ChatClient field named chatClient)
        String answer;
        try {
            answer = chatClient
                    .prompt()
                    .system("You are a helpful pet healthcare assistant. Be concise and safe.")
                    .user(question)
                    .call()
                    .content();
        } catch (Exception ex) {
            Map<String, String> err = new HashMap<>();
            err.put("error", "Failed to get an answer from the AI service.");
            err.put("details", ex.getMessage());
            return ResponseEntity.internalServerError().body(err);
        }

        // 3) Build and return success JSON
        Map<String, String> result = new HashMap<>();
        result.put("question", question);
        result.put("answer", answer);
        return ResponseEntity.ok(result);
    }
}
