You are a senior full-stack engineer and software architecture mentor.

I am building a simple educational demo for students learning Java Spring Boot.

The purpose of this project is NOT to build a production chatbot.

The purpose is to demonstrate:

1. A Spring Boot application acting as a middleware layer.
2. A Python FastAPI service acting as an AI service.
3. Communication between Spring Boot and FastAPI through REST API calls.
4. Server-side HTML rendering using Thymeleaf.
5. Basic request flow between frontend, Spring Boot, and Python.

====================================================
PROJECT GOAL
============

Build a simple chatbot application with the following architecture:

Browser
↓
Spring Boot + Thymeleaf
↓
FastAPI
↓
LLM Response chatbot use `chatbot.py`

Students should clearly understand the flow:

User enters message
→ Spring Boot receives message
→ Spring Boot calls FastAPI
→ FastAPI generates response
→ Spring Boot receives response
→ Thymeleaf renders updated page

====================================================
TECH STACK
==========

Frontend:

* Thymeleaf
* HTML only (see if its possible if not ask me for the permission to use js)

Spring Boot:

* Spring Boot MVC


Python:

* FastAPI

====================================================
IMPORTANT RESTRICTIONS
======================

DO NOT USE:

* JavaScript
* CSS
* React
* Vue
* Bootstrap

This is intentionally a simple server-rendered educational demo.

====================================================
STRUCTURE
=====================

the structure should be simple but is used in real world production

====================================================


====================================================
FUNCTIONAL REQUIREMENTS
=======================

The webpage contains:

Title:
"Spring Boot + FastAPI Chatbot Demo"

A simple form:
first give a plain text field, showing a prompt for example

chatbox: Message Input, Send Button, clear button

After submission: 

Display:

User: <user message>

Bot: <bot response>


TEACHING REQUIREMENT
====================

Add comments throughout the code explaining:

1. What the controller does
2. What the service does
3. Why Spring Boot calls FastAPI
4. Request flow
5. Response flow

The code should be educational and easy for beginners to follow.

====================================================
LLM REQUIREMENT
Prompt builder, which should be handled by python code
No conversation history needed, but we need a context field that use python to open data inside resources/data


REQUEST FLOW DIAGRAM
====================

Include the following architecture explanation in README:

Browser
↓
Thymeleaf Form
↓
Spring MVC Controller
↓
Spring Service
↓
HTTP POST
↓
FastAPI
↓
Mock LLM Response
↓
Spring Boot
↓
Thymeleaf View
↓
Browser

====================================================
README REQUIREMENTS
===================

No readme first

====================================================
CODE QUALITY
============

Use clean MVC architecture.

Keep controllers thin.

Put HTTP communication logic in service layer.

Use DTOs for request and response.

Keep code simple and suitable for classroom demonstration.
