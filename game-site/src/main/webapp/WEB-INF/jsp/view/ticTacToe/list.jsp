<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- @elvariable id="pendingGames" type="java.util.Map<Long, java.lang.String>" --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        <%-- Added for Eclipse --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  <%-- Added for Eclipse --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Game Site :: Tic Tac Toe</title>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>

    <h2>Tic Tac Toe</h2>
    
    <p><a href="javascript:void 0" onclick="startGame();">Start a Game</a></p>
    
    <c:choose>
        <c:when test="${fn:length(pendingGames) == 0}">
            <i>There are no existing games to join.</i>
        </c:when>
        <c:otherwise>
            Join a game waiting for a second player:<br />
            <c:forEach items="${pendingGames}" var="entry">
                <a href="javascript:void 0;"
                    onclick="joinGame(${entry.key})">User: ${entry.value}</a><br />
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <script type="text/javascript">
        var startGame, joinGame;
        $(document).ready(function() {
            var url = '<c:url value="/ticTacToe" />';

            var validateUsername = function(username) {
                var valid = (username.match(/^[a-zA-Z0-9_]+$/) != null);
                if (!valid) {
                    alert('User names can only contain letters, numbers and underscores.');
                }
                return valid;
            };

            var post = function(fields) {
                var form = $('<form id="mapForm" method="post"></form>')
                    .attr({action: url, style: 'display: none;'});
                for (var key in fields) {
                    if (fields.hasOwnProperty(key)) {
                        form.append($('<input type="hidden">').attr({name: key, value: fields[key]}));
                    }
                }
                $('body').append(form);
                form.submit();
            };

            startGame = function() {
                var username = prompt('Enter a username to start a game.', '');
                if (username != null && username.trim().length > 0 
                        && validateUsername(username)) {
                    post({action: 'start', username: username});
                }
            };

            joinGame = function(gameId) {
            	var username = prompt('Enter a username to join a game.', '');
            	if (username != null && username.trim().length > 0 
                        && validateUsername(username)) {
                    post({action: 'join', username: username, gameId: gameId});
                }
            }
        });
    </script>
</body>
</html>