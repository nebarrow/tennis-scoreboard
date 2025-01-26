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
        <div class="start-match-header">
            <h1>Start new match</h1>
            <img src="images/racket.png" alt="Guy with racket" class="racket-picture">
        </div>
        <div class="players-form">
            <form id="matchForm" action="new-match" method="POST">
                <div class="input-group">
                    <span class="error-message" id="error-player"></span>
                    <label for="playerOne">Player one</label>
                    <input type="text" id="playerOne" name="playerOne" placeholder="Name" required>
                    <label for="playerTwo">Player two</label>
                    <input type="text" id="playerTwo" name="playerTwo" placeholder="Name" required>
                </div>
                <button type="submit" class="second-start-button">Start</button>
            </form>
        </div>
    </main>
    <script src="validation.js"></script>
</body>
</html>