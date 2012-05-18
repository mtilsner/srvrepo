

<%@ page import="srvrepo.Quality" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quality.label', default: 'Quality')}" />
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
            <g:hasErrors bean="${qualityInstance}">
            <div class="errors">
                <g:renderErrors bean="${qualityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save"  enctype="multipart/form-data">
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="value"><g:message code="quality.service.label" default="Service" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: qualityInstance, field: 'service', 'errors')}">
                                    <g:select name="service" from="${srvrepo.Service.list()}" optionKey="id" value="${qualityInstance?.service?.id}" optionValue="name" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="quality.keyword.label" default="Keyword" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: qualityInstance, field: 'keyword', 'errors')}">
									<g:select name="keyword" from="${srvrepo.Keyword.list()}" optionKey="id" value="${qualityInstance?.keyword?.id}" optionValue="name" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="value"><g:message code="quality.value.label" default="Value" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: qualityInstance, field: 'value', 'errors')}">
                                    <g:textArea name="value" value="${qualityInstance?.value}" cols="40" rows="5" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="file"><g:message code="quality.file.label" default="File" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: qualityInstance, field: 'file', 'errors')}">
                                    <input type="file" id="file" name="file" />
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
