
<%@ page import="srvrepo.Predicate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'predicate.label', default: 'Predicate')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'predicate.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'predicate.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'predicate.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="input" title="${message(code: 'predicate.input.label', default: 'Input')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${predicateInstanceList}" status="i" var="predicateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${predicateInstance.id}">${fieldValue(bean: predicateInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: predicateInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: predicateInstance, field: "description")}</td>
                        
                            <td><cocktail:typeLink type="${predicateInstance.input}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${predicateInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
