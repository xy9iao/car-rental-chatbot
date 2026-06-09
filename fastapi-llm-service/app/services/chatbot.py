import os
from pathlib import Path

from dotenv import load_dotenv
from openai import OpenAI


load_dotenv()

PROJECT_ROOT = Path(__file__).resolve().parents[3]
DATA_DIR = PROJECT_ROOT / "src" / "main" / "resources" / "data"
MODEL = "meta-llama/llama-3-8b-instruct"


def load_context_from_data():
    context_parts = []

    for path in sorted(DATA_DIR.glob("*.txt")):
        text = path.read_text(encoding="utf-8")
        context_parts.append(f"=== {path.name} ===\n{text}")

    return "\n\n".join(context_parts)


def create_openrouter_client():
    api_key = os.getenv("OPENROUTER_API_KEY")

    if not api_key:
        raise RuntimeError("OPENROUTER_API_KEY is not set.")

    return OpenAI(
        base_url="https://openrouter.ai/api/v1",
        api_key=api_key,
    )


def build_system_prompt():
    context = load_context_from_data()

    return f"""
            You are a car rental assistant.

            Role:
            You help customers with car rental questions.

            Task:
            Answer questions about vehicle types, rental dates, prices, booking steps, and rental policies.

            Constraints:
            - Only answer car rental related questions.
            - If the question is unrelated, politely refuse.
            - Use the context field below as your source of truth.
            - Do not invent prices, policies, cars, branch hours, or availability.
            - If important booking information is missing, ask a follow-up question.

            Context:
            {context}

            Output format:
            Use short and clear answers.

            Examples:

            User: I need a car for 6 people.
            Assistant: A 7-seater may be suitable. What pickup date and return date do you need?

            User: Tell me a joke.
            Assistant: Sorry, I can only assist with car rental related enquiries.

            User: How much is an SUV?
            Assistant: I can help with that, but I need the rental date and pickup location first.
            """.strip()


def generate_reply(question: str) -> str:
    client = create_openrouter_client()

    response = client.chat.completions.create(
        model=MODEL,
        messages=[
            {
                "role": "system",
                "content": build_system_prompt(),
            },
            {
                "role": "user",
                "content": question,
            },
        ],
    )

    return response.choices[0].message.content


if __name__ == "__main__":
    question = input("Ask the chatbot: ")
    print(generate_reply(question))
