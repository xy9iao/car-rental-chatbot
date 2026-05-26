# AI Car Rental Chatbot

This project is a simple Java Spring Boot web application that connects a car rental chatbot to an AI API.

It extends the Prompt Engineering workshop car rental example into a standalone web app with:

- a simple frontend chat UI
- a Spring Boot REST API
- text-file based car rental dataset context
- conversation history
- prompt engineering rules
- AI API communication

## Tech Stack

- Java 17
- Spring Boot
- Maven
- HTML
- CSS
- JavaScript
- OpenAI API

## Application Flow
1. User types message in frontend
2. POST /api/chat
3. ChatController
4. ChatService
5. DatasetContextService loads .txt dataset files
6. ConversationHistoryService provides recent chat history
7. PromptBuilder builds the final prompt
8. AIClient calls OpenAI API
9. Chatbot reply is returned to frontend

## Features

* `POST /api/chat` endpoint for sending user messages
* `DELETE /api/chat/history` endpoint for clearing conversation history
* Prompt engineering rules for car rental chatbot behavior
* Text dataset files injected into the prompt
* In-memory conversation history for follow-up questions
* Clear button resets frontend chat and backend history

## API Key Setup
```powershell
$env:OPENROUTER_API_KEY="your_openrouter_api_key_here"
```

## Configuration
In `src/main/resources/application.properties`:
```properties
spring.application.name=car-rental-chatbot

server.port=8080

ai.api.key=${OPENROUTER_API_KEY}
ai.api.url=https://openrouter.ai/api/v1/chat/completions
ai.model=openai/gpt-oss-120b:free
ai.app.name=Car Rental Chatbot
```

## Run the Application
From the project root:
```powershell
.\mvnw spring-boot:run
```

Then open the app in your browser:
```text
http://localhost:8080
```

## Example Questions

Try asking:

```text
I need a cheap SUV.
```

```text
Do you have a 7-seater family car?
```

```text
What is the insurance policy?
```

```text
What time is the branch open?
```

```text
What is the late return policy?
```
