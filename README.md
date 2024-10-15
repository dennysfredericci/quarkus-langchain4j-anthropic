# quarkus-langchain4j-anthropic

The intent of this project is to reproduce an issue from quarkus-langchain4j 0.20.3


## How to reproduce the issue

Open the application.properties file and set the following property to true:

```
quarkus.langchain4j.anthropic.chat-model.enabled
```

Then run the application and you will see the following error:

```
 io.quarkus.runtime.configuration.ConfigurationException: A ChatLanguageModel or StreamingChatLanguageModel bean was requested, but no langchain4j providers were configured. Consider adding an extension like 'quarkus-langchain4j-openai'
```

The quick fix is by removing the property from the application.properties file.

## Root cause

The root cause seems to be in [AnthropicProcessor.providerCandidates](https://github.com/quarkiverse/quarkus-langchain4j/blob/b3bd6cfaf6e53038e96356fd6123240fb9b433e0/model-providers/anthropic/deployment/src/main/java/io/quarkiverse/langchain4j/anthropic/deployment/AnthropicProcessor.java#L35).

The if statement does not check if the property is enabled or not, it only checks if it is empty.

```java
if (config.chatModel().enabled().isEmpty()) {
    chatProducer.produce(new ChatModelProviderCandidateBuildItem(PROVIDER));
}
```

To fix the issue, the if statement should be updated to check if the property is enabled or not.

```java
if (config.chatModel().enabled().isEmpty() || config.chatModel().enabled().get()) {
    chatProducer.produce(new ChatModelProviderCandidateBuildItem(PROVIDER));
}
```

Looks like we have the same issue at VertexAiGeminiProcessor and VertexAiProcessor.


