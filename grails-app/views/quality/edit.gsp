

<%@ page import="srvrepo.Quality" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'quality.label', default: 'Quality')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${qualityInstance}">
            <div class="errors">
                <g:renderErrors bean="${qualityInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post"  enctype="multipart/form-data">
                <g:hiddenField name="id" value="${qualityInstance?.id}" />
                <g:hiddenField name="version" value="${qualityInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                
                    		<tr class="prop">
                        		<td valign="top" class="name"><g:message code="quality.service.label" default="Service" /></td>
                        
                        		<td valign="top" class="value"><g:link controller="service" action="show" id="${qualityInstance?.service?.id}">${fieldValue(bean: qualityInstance?.service, field: "name")}</g:link></td>
                        
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
                        		<td valign="top" class="name"><g:message code="quality.file.label" default="File" /></td>

                        		<td valign="top" class="value"><g:link action="download" id="${qualityInstance.id}">[Datei herunterladen]</g:link></td>

                    		</tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
