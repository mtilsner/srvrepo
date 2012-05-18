
<%@ page import="srvrepo.Keyword" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'keyword.label', default: 'Keyword')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'keyword.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'keyword.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="comparator" title="${message(code: 'keyword.comparator.label', default: 'Comparator')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${keywordInstanceList}" status="i" var="keywordInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${keywordInstance.id}">${fieldValue(bean: keywordInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: keywordInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: keywordInstance, field: "comparator")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${keywordInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
