<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Cadence - Web Hooks</title>
</head>

<body>
<h1>Web Hooks</h1>
<ul>
    <li>Jira: <strong>${jira}</strong></li>
    <li>Fisheye: <strong>${fisheye}</strong></li>
    <li>Bamboo: <strong>${bamboo}</strong></li>
</ul>

<div>
    <g:each in="${list}" var="event">
        <p>[<g:formatDate date="${event.date}" format="dd.MM.yyyy HH:mm:ss"/>] Event by ${event.type}: ${event.story} ${event.action} ${event.info}</p>
    </g:each>
</div>
</body>
</html>
