$ErrorActionPreference = "Stop"

Set-Location (Split-Path -Parent $MyInvocation.MyCommand.Path)

Write-Host "Installing Python dependencies..."
python -m pip install -r fastapi-llm-service/requirements.txt

Write-Host "Starting FastAPI on http://localhost:8000 ..."
$fastApi = Start-Process `
    -FilePath "python" `
    -ArgumentList @("-m", "uvicorn", "app.main:app", "--reload", "--port", "8000") `
    -WorkingDirectory "fastapi-llm-service" `
    -NoNewWindow `
    -PassThru

Start-Sleep -Seconds 3

try {
    Write-Host "Starting Spring Boot on http://localhost:8080 ..."
    Write-Host "Open http://localhost:8080 in your browser."
    .\mvnw.cmd spring-boot:run
}
finally {
    Write-Host "Stopping FastAPI..."
    Stop-Process -Id $fastApi.Id -Force
}
