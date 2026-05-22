const chatBox = document.getElementById("chat-box");
const messageInput = document.getElementById("message-input");
const sendButton = document.getElementById("send-button");

sendButton.addEventListener("click", sendMessage);

messageInput.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        sendMessage();
    }
});

async function sendMessage() {
    const message = messageInput.value.trim();

    if (!message) {
        return;
    }

    addMessage(message, "user");
    messageInput.value = "";

    addMessage("Thinking...", "bot");

    try {
        const response = await fetch("/api/chat", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                message: message
            })
        });

        const data = await response.json();

        removeLastBotThinkingMessage();
        addMessage(data.reply, "bot");

    } catch (error) {
        removeLastBotThinkingMessage();
        addMessage("Error: Could not connect to backend.", "bot");
        console.error(error);
    }
}

function addMessage(text, sender) {
    const messageElement = document.createElement("div");
    messageElement.classList.add("message");
    messageElement.classList.add(sender);
    messageElement.textContent = text;

    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

function removeLastBotThinkingMessage() {
    const messages = chatBox.querySelectorAll(".message.bot");
    const lastBotMessage = messages[messages.length - 1];

    if (lastBotMessage && lastBotMessage.textContent === "Thinking...") {
        lastBotMessage.remove();
    }
}