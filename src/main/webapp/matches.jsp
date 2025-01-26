<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Ubuntu:ital,wght@0,300;0,400;0,500;0,700;1,300;1,400;1,500;1,700&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <title>Tennis Scoreboard</title>
</head>
<body>
<header>
    <h2 class="header-in-left-corner">Tennis Scoreboard</h2>
    <div class="button-container">
        <a href="${pageContext.request.contextPath}/">
            <button id="home-button" class="home-button">Home</button>
        </a>
        <a href="${pageContext.request.contextPath}/matches">
            <button id="matches-button" class="matches-button">Matches</button>
        </a>
    </div>
</header>
<hr class="divider"/>
<main>
    <div class="matches-header">
        <h1>Matches</h1>
    </div>
    <form method="get" action="${pageContext.request.contextPath}/matches">
        <div class="filter-container">
            <div class="reset-button">
                <a href="${pageContext.request.contextPath}/matches">
                    <button type="button" class="filter-button">Reset Filter</button>
                </a>
            </div>
            <input class="filter-line" name="filter" placeholder="Filter by name" type="text"/>
        </div>
    </form>
    <table class="matches-table">
        <tr>
            <th>Player One</th>
            <th>Player Two</th>
            <th>Winner</th>
        </tr>
        <c:forEach var="match" items="${requestScope.matches}">
            <tr>
                <td>${match.playerOne()}</td>
                <td>${match.playerTwo()}</td>
                <td><span class="winner-name-td">${match.winner()}</span></td>
            </tr>
        </c:forEach>
    </table>

    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a class="prev"
               href="${pageContext.request.contextPath}/matches?page=${currentPage - 1}&filter=${param.filter}"> < </a>
        </c:if>
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a class="num-page ${i == currentPage ? 'current' : ''}"
               href="${pageContext.request.contextPath}/matches?page=${i}">
                    ${i}
            </a>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <a class="next" href="${pageContext.request.contextPath}/matches?page=${currentPage + 1}"> > </a>
        </c:if>
    </div>
</main>
</body>
</html>