from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

from .chatbot import generate_reply


class PythonChatRequest(BaseModel):
    message: str


class PythonChatResponse(BaseModel):
    reply: str


app = FastAPI(title="Car Rental Python Chatbot Gateway")


@app.get("/")
def root():
    return {
        "message": "FastAPI is running. Open the Spring Boot app at http://localhost:8080"
    }


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/api/python-chat", response_model=PythonChatResponse)
def python_chat(request: PythonChatRequest):
    if request.message is None or not request.message.strip():
        raise HTTPException(status_code=400, detail="Message is required.")

    try:
        reply = generate_reply(request.message)
    except Exception as error:
        raise HTTPException(status_code=500, detail=str(error)) from error

    return PythonChatResponse(reply=reply)
