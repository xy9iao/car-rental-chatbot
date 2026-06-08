# Spring Boot + FastAPI Chatbot Demo

This project is a small teaching demo. It shows how a Spring Boot web app can call a Python FastAPI service.

## Flow

```text
Browser
  -> Thymeleaf form
  -> Spring MVC Controller
  -> Spring Service
  -> HTTP POST to FastAPI /chat
  -> Python chatbot builds prompt with src/main/resources/data
  -> OpenRouter LLM
  -> FastAPI returns JSON
  -> Spring Boot renders Thymeleaf page
  -> Browser
```

Students should notice that Spring Boot is the web app, while FastAPI is a separate AI service.

## Folders

```text
src/main/java/                 Spring Boot code
src/main/resources/templates/  Thymeleaf HTML page
src/main/resources/data/       Text files used as chatbot context
fastapi-llm-service/           FastAPI LLM service
start.ps1                      Starts both services
```

## Run

From the project root:

```powershell
$env:OPENROUTER_API_KEY="your_openrouter_key"
.\start.ps1
```

If PowerShell blocks the script:

```powershell
powershell -ExecutionPolicy Bypass -File .\start.ps1
```

Open:

```text
http://localhost:8080
```

## Ports

```text
8080 = Spring Boot web app
8000 = FastAPI AI service
```

Students only open `http://localhost:8080`. Spring Boot calls FastAPI internally.

## Manual Run

Terminal 1:

```powershell
$env:OPENROUTER_API_KEY="your_openrouter_key"
python -m pip install -r fastapi-llm-service/requirements.txt
python -m uvicorn app.main:app --reload --port 8000 --app-dir fastapi-llm-service
```

Terminal 2:

```powershell
.\mvnw.cmd spring-boot:run
```
