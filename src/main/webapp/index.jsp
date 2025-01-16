<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,300;0,400;0,500;0,700;1,300;1,400;1,500;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <title>Tennis Scoreboard</title>
</head>
<body>
    <header>
        <h2 class="header-in-left-corner">Tennis Scoreboard</h2>
        <div class="button-container">
            <a  href="${pageContext.request.contextPath}/">
                <button id="home-button" class="home-button">Home</button>
            </a>
            <a href="${pageContext.request.contextPath}/matches">
                <button id="matches-button" class="matches-button">Matches</button>
            </a>
        </div>
    </header>
    <hr class="divider" />
    <main>
        <div class="welcome-header">
            <h1>Welcome to the TennisScoreboard!</h1>
            <p>Manage your tennis matches, record results, and track rankings</p>
            <img src="images/welcome_image.png" alt="Welcome Image" class="welcome-image">
        </div>
        <div class="buttons-under-picture">
            <a href="${pageContext.request.contextPath}/new-match">
                <button id="start-button" class="start-button">Start a new match</button>
            </a>
            <a href="${pageContext.request.contextPath}/matches">
                <button id="results-button" class="results-button">View match results</button>
            </a>
        </div>
    </main>
</body>
</html>