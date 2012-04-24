

<%@ page import="srvrepo.Predicate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'predicate.label', default: 'Predicate')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${predicateInstance}">
            <div class="errors">
                <g:renderErrors bean="${predicateInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="predicate.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: predicateInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${predicateInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="predicate.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: predicateInstance, field: 'description', 'errors')}">
                                    <g:textArea name="description" value="${predicateInstance?.description}" cols="40" rows="5" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="input"><g:message code="predicate.input.label" default="Input" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: predicateInstance, field: 'input', 'errors')}">
                                    <cocktail:typeSelector name="input" value="${predicateInstance?.input}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
