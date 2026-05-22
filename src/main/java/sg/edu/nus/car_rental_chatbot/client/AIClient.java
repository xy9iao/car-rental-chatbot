package sg.edu.nus.car_rental_chatbot.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AIClient {

    @Value("${ai.api.key}")
    private String apiKey;

    @Value("${ai.api.url}")
    private String apiUrl;

    @Value("${ai.model}")
    private String model;

    @Value("${ai.app.name}")
    private String appName;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String callAI(String prompt) {
        try {
            String requestBody = """
                    {
                      "model": "%s",
                      "messages": [
                        {
                          "role": "user",
                          "content": %s
                        }
                      ],
                      "temperature": 0.3
                    }
                    """.formatted(model, toJsonString(prompt));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("HTTP-Referer", "http://localhost:8080")
                    .header("X-OpenRouter-Title", appName)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            return extractAssistantReply(response.body());

        } catch (Exception e) {
            return "OpenRouter API call failed: " + e.getMessage();
        }
    }

    private String toJsonString(String text) {
        return "\"" + text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                + "\"";
    }

    private String extractAssistantReply(String responseBody) {
        String marker = "\"content\":\"";
        int start = responseBody.indexOf(marker);

        if (start == -1) {
            return "Could not extract assistant reply. Raw response: " + responseBody;
        }

        start += marker.length();

        StringBuilder result = new StringBuilder();
        boolean escaping = false;

        for (int i = start; i < responseBody.length(); i++) {
            char c = responseBody.charAt(i);

            if (escaping) {
                switch (c) {
                    case 'n':
                        result.append('\n');
                        break;
                    case 'r':
                        result.append('\r');
                        break;
                    case 't':
                        result.append('\t');
                        break;
                    case '"':
                        result.append('"');
                        break;
                    case '\\':
                        result.append('\\');
                        break;
                    default:
                        result.append(c);
                        break;
                }
                escaping = false;
            } else {
                if (c == '\\') {
                    escaping = true;
                } else if (c == '"') {
                    break;
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}