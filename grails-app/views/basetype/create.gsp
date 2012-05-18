

<%@ page import="srvrepo.Basetype" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'basetype.label', default: 'Basetype')}" />
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
            <g:hasErrors bean="${basetypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${basetypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="basetype.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basetypeInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${basetypeInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="supertype"><g:message code="basetype.supertype.label" default="Supertype" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basetypeInstance, field: 'supertype', 'errors')}">
                                    <g:select name="supertype.id" from="${srvrepo.Basetype.list()}" optionKey="id" value="${basetypeInstance?.supertype?.id}" noSelection="[null:'-- kein Supertyp --']" />
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
