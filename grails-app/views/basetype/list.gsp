
<%@ page import="srvrepo.Basetype" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'basetype.label', default: 'Basetype')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'basetype.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'basetype.name.label', default: 'Name')}" />
                        
                            <th><g:message code="basetype.supertype.label" default="Supertype" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${basetypeInstanceList}" status="i" var="basetypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${basetypeInstance.id}">${fieldValue(bean: basetypeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: basetypeInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: basetypeInstance, field: "supertype")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${basetypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
