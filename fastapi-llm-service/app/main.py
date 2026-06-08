from fastapi import FastAPI

from app.routers.chat_router import router as chat_router


app = FastAPI(title="Car Rental LLM Service")

app.include_router(chat_router)


@app.get("/")
def root():
    return {
        "message": "FastAPI LLM service is running. Open the Spring Boot app at http://localhost:8080"
    }


@app.get("/health")
def health():
    return {"status": "ok"}
