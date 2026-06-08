$ErrorActionPreference = "Stop"

$ProjectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $ProjectRoot

function Get-PythonCommand {
    if (Get-Command python -ErrorAction SilentlyContinue) {
        return "python"
    }

    if (Get-Command py -ErrorAction SilentlyContinue) {
        return "py"
    }

    throw "Python was not found. Install Python, then run this script again."
}

function Test-Port {
    param (
        [string] $HostName,
        [int] $Port
    )

    $Client = New-Object System.Net.Sockets.TcpClient
    try {
        $Connection = $Client.BeginConnect($HostName, $Port, $null, $null)
        if (-not $Connection.AsyncWaitHandle.WaitOne(1000, $false)) {
            return $false
        }

        $Client.EndConnect($Connection)
        return $true
    }
    catch {
        return $false
    }
    finally {
        $Client.Close()
    }
}

$PythonCommand = Get-PythonCommand
$MavenWrapper = Join-Path $ProjectRoot "mvnw.cmd"
$FastApiProcess = $null

if (-not (Test-Path $MavenWrapper)) {
    throw "Maven wrapper was not found: $MavenWrapper"
}

try {
    Write-Host "Installing Python dependencies..."
    & $PythonCommand -m pip install -r "python_api/requirements.txt"

    Write-Host "Starting FastAPI on http://localhost:8000 ..."
    $FastApiProcess = Start-Process `
        -FilePath $PythonCommand `
        -ArgumentList @("-m", "uvicorn", "python_api.main:app", "--reload", "--port", "8000") `
        -NoNewWindow `
        -PassThru

    $FastApiReady = $false
    for ($attempt = 1; $attempt -le 10; $attempt++) {
        if (Test-Port -HostName "localhost" -Port 8000) {
            $FastApiReady = $true
            break
        }

        Start-Sleep -Seconds 1
    }

    if (-not $FastApiReady) {
        throw "FastAPI did not start correctly. Check the Python logs above."
    }

    Write-Host "Starting Spring Boot on http://localhost:8080 ..."
    Write-Host "Open http://localhost:8080 in your browser."
    & $MavenWrapper spring-boot:run
}
finally {
    if ($FastApiProcess -and -not $FastApiProcess.HasExited) {
        Write-Host "Stopping FastAPI..."
        Stop-Process -Id $FastApiProcess.Id -Force
    }
}
