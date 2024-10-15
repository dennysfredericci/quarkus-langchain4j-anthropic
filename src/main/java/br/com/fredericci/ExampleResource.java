package br.com.fredericci;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {
    
    private final ChatService chatService;
    
    public ExampleResource(ChatService chatService) {
        this.chatService = chatService;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return chatService.getJoke();
    }
}
