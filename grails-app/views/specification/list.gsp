
<%@ page import="srvrepo.Specification" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'specification.label', default: 'Specification')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'specification.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'specification.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="description" title="${message(code: 'specification.description.label', default: 'Description')}" />
                        
                            <g:sortableColumn property="input" title="${message(code: 'specification.input.label', default: 'Input')}" />
                        
                            <g:sortableColumn property="output" title="${message(code: 'specification.output.label', default: 'Output')}" />
                        
<!--                            <g:sortableColumn property="precondition" title="${message(code: 'specification.precondition.label', default: 'Pre-condition')}" />

                            <g:sortableColumn property="postcondition" title="${message(code: 'specification.postcondition.label', default: 'Post-condition')}" />-->
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${specificationInstanceList}" status="i" var="specificationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${specificationInstance.id}">${fieldValue(bean: specificationInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: specificationInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: specificationInstance, field: "description")}</td>
                        
                            <td><cocktail:typeLink type="${specificationInstance.input}" /></td>
                        
                            <td><cocktail:typeLink type="${specificationInstance.output}" /></td>

<!--                            <td><cocktail:formulaLink formula="${specificationInstance.precondition}" /></td>
                        
                            <td><cocktail:formulaLink formula="${specificationInstance.postcondition}" /></td>-->
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${specificationInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
