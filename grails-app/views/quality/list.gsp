
<%@ page import="srvrepo.Quality" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quality.label', default: 'Quality')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
			<auth:hasOneRole roles="['REPOSITORY_ADMINISTRATOR']">
            	<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
			</auth:hasOneRole>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'quality.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="file" title="${message(code: 'quality.service.label', default: 'Service')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'quality.keyword.label', default: 'Keyword')}" />
                        
                            <g:sortableColumn property="value" title="${message(code: 'quality.value.label', default: 'Value')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${qualityInstanceList}" status="i" var="qualityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${qualityInstance.id}">${fieldValue(bean: qualityInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: qualityInstance?.service, field: "name")}</td>
                        
                            <td>${fieldValue(bean: qualityInstance?.keyword, field: "name")}</td>
                        
                            <td>${fieldValue(bean: qualityInstance, field: "value")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${qualityInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
