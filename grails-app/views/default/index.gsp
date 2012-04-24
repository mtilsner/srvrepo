

<%@ page import="srvrepo.Predicate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'predicate.label', default: 'Predicate')}" />
        <title>Service Repository</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="default.available-functions.label" default="Verfügbare Funktionen" /></h1>
			<ul>
				<li><g:link controller="basetype" action="list">Datentypen verwalten</g:link></li>
				<li><g:link controller="predicate" action="list">Predicates verwalten</g:link></li>
				<li><g:link controller="keyword" action="list">Keywords verwalten</g:link></li>
				<li><g:link controller="vendor" action="list">Dienstanbieter verwalten</g:link></li>
				<li><g:link controller="specification" action="list">Spezifikationen verwalten</g:link></li>
				<li><g:link controller="service" action="list">Dienste verwalten</g:link></li>
				<li><g:link controller="quality" action="list">Dienstgütebeschreibungen verwalten</g:link></li>
			</ul>
		</div>
	</body>
</html>