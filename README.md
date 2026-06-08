# Spring Boot + FastAPI Chatbot Demo

This is a simple educational demo for students learning Java Spring Boot MVC.
It shows Spring Boot acting as middleware between a server-rendered HTML page and a Python FastAPI AI service.

## Architecture

```text
Browser
-> Thymeleaf Form
-> Spring MVC Controller
-> Spring Service
-> HTTP POST
-> FastAPI
-> Mock LLM Response
-> Spring Boot
-> Thymeleaf View
-> Browser
```

## Request Flow

1. User enters a message in the Thymeleaf form.
2. Spring MVC receives the form submission.
3. The controller passes the message to `ChatService`.
4. `ChatService` sends an HTTP POST request to FastAPI.
5. FastAPI calls `python_api/chatbot.py`.
6. Python opens `src/main/resources/data`, builds the prompt, and returns a mock chatbot response.
7. Spring Boot receives the response.
8. Thymeleaf renders the updated page with:
   - `User: <user message>`
   - `Bot: <bot response>`

## Run Everything

Use one command from the project root:

```powershell
.\start.ps1
```

If PowerShell blocks scripts on your machine, run:

```powershell
powershell -ExecutionPolicy Bypass -File .\start.ps1
```

Then open:

```text
http://localhost:8080
```

## Run Manually

Start FastAPI:

```powershell
python -m pip install -r python_api/requirements.txt
python -m uvicorn python_api.main:app --reload --port 8000
```

Open another terminal and start Spring Boot:

```powershell
.\mvnw spring-boot:run
```

Then open:

```text
http://localhost:8080
```
