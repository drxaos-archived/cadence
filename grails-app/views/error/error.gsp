<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Ошибка приложения</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css"></g:if>
	</head>
	<body>
		<g:if env="development">
			<g:renderException exception="${exception}" />
		</g:if>
		<g:else>
			<ul class="errors">
				<li>Произошла ошибка. Пожалуйста обратитесь в техподдержку.</li>
			</ul>
		</g:else>
	</body>
</html>
