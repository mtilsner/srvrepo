

<%@ page import="srvrepo.Specification" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'specification.label', default: 'Specification')}" />
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
            <g:hasErrors bean="${specificationInstance}">
            <div class="errors">
                <g:renderErrors bean="${specificationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="specification.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: specificationInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${specificationInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="specification.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: specificationInstance, field: 'description', 'errors')}">
                                    <g:textArea name="description" value="${specificationInstance?.description}" cols="40" rows="5" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="input"><g:message code="specification.input.label" default="Input" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: specificationInstance, field: 'input', 'errors')}">
                                    <cocktail:typeSelector name="input" value="${specificationInstance?.input}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="output"><g:message code="specification.output.label" default="Output" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: specificationInstance, field: 'output', 'errors')}">
                                    <cocktail:typeSelector name="output" value="${specificationInstance?.output}" />
                                </td>
                            </tr>
                        
                    
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="precondition"><g:message code="specification.precondition.label" default="Pre-condition" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: specificationInstance, field: 'precondition', 'errors')}">
                                <cocktail:formulaSelector name="precondition" value="${specificationInstance?.precondition}" />
                            </td>
                        </tr>
                    
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="postcondition"><g:message code="specification.postcondition.label" default="Post-condition" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: specificationInstance, field: 'postcondition', 'errors')}">
                                    <cocktail:formulaSelector name="postcondition" value="${specificationInstance?.postcondition}" />
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
