# FastAPI LLM Service

This service exposes a `/chat` API endpoint for the Spring Boot application.

## Structure

```text
app/main.py                 FastAPI app entry point
app/routers/chat_router.py  Defines the /chat endpoint
app/services/chatbot.py     Core chatbot and OpenRouter logic
requirements.txt            Python dependencies
```

## Run

From the project root:

```powershell
$env:OPENROUTER_API_KEY="your_openrouter_key"
python -m pip install -r fastapi-llm-service/requirements.txt
python -m uvicorn app.main:app --reload --port 8000 --app-dir fastapi-llm-service
```
