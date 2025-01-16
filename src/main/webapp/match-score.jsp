<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <div class="current-match-header">
            <h1>Current match</h1>
            <img src="images/scorekeeper.png" alt="Racket" class="racket-with-ball-picture">
        </div>
        <table class="score-table">
            <thead>
                <tr>
                    <th>Player</th>
                    <th>Sets</th>
                    <th>Games</th>
                    <th>Points</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${requestScope.matchScore.getPlayers().get(0).getName()}</td>
                    <td>${requestScope.matchScore.getPlayers().get(0).getSets()}</td>
                    <td>${requestScope.matchScore.getPlayers().get(0).getGames()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${requestScope.matchScore.getPlayers().get(0).getGames() == 6 && requestScope.matchScore.getPlayers().get(1).getGames() == 6}">
                                ${requestScope.matchScore.getPlayers().get(0).getPoints()}
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(0).getPoints() == 0}">0</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(0).getPoints() == 1}">15</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(0).getPoints() == 2}">30</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(0).getPoints() == 3}">40</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(0).getPoints() == 4}">AD</c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="match-score?uuid=${uuid}" method="post">
                            <input type="hidden" name="player" value="${requestScope.matchScore.getPlayers().get(0).getId()}">
                            <button class="score-btn" type="submit">Score</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>${requestScope.matchScore.getPlayers().get(1).getName()}</td>
                    <td>${requestScope.matchScore.getPlayers().get(1).getSets()}</td>
                    <td>${requestScope.matchScore.getPlayers().get(1).getGames()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${requestScope.matchScore.getPlayers().get(0).getGames() == 6 && requestScope.matchScore.getPlayers().get(1).getGames() == 6}">
                                ${requestScope.matchScore.getPlayers().get(1).getPoints()}
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(1).getPoints() == 0}">0</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(1).getPoints() == 1}">15</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(1).getPoints() == 2}">30</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(1).getPoints() == 3}">40</c:when>
                                    <c:when test="${requestScope.matchScore.getPlayers().get(1).getPoints() == 4}">AD</c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="match-score?uuid=${uuid}" method="post">
                            <input type="hidden" name="player" value="${requestScope.matchScore.getPlayers().get(1).getId()}">
                            <button class="score-btn" type="submit">Score</button>
                        </form>
                </tr>
            </tbody>
        </table>
    </main>
</body>
</html>