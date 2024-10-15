package br.com.fredericci;


import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface ChatService {
    
    @UserMessage("tell me a joke")
    String getJoke();
}
