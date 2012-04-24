
<%@ page import="srvrepo.Service" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'service.label', default: 'Service')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
			<auth:hasOneRole roles="['REPOSITORY_ADMINISTRATOR','SERVICE_ADMINISTRATOR']">
            	<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
			</auth:hasOneRole>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="service.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: serviceInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="service.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: serviceInstance, field: "name")}</td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="service.activated.label" default="Activated" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${serviceInstance.activated}" /></td>
                            
                        </tr>

						<tr class="prop">
							<td valign="top" class="name"><g:message code="service.url.label" default="URL" /></td>
							
							<td valign="top" class="value"><a href="${serviceInstance.url}" target="_new">${fieldValue(bean: serviceInstance, field: "url")}</a></td>
							
						</tr>

						<tr class="prop">
							<td valign="top" class="name"><g:message code="service.qualities.label" default="Qualities" /></td>
							
							<td valign="top" style="text-align: left;" class="value">
								<ul>
								<g:each in="${serviceInstance.qualities}" var="q">
									<li><g:link controller="quality" action="show" id="${q.id}">${fieldValue(bean: q?.keyword, field: "name")}</g:link></li>
								</g:each>
								</ul>
							</td>
						</tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="service.specifications.label" default="Specifications" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${serviceInstance.specifications}" var="s">
                                    <li><cocktail:specificationLink specification="${s}" /></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="service.vendor.label" default="Vendor" /></td>
                            
                            <td valign="top" class="value"><g:link controller="vendor" action="show" id="${serviceInstance?.vendor?.id}">${serviceInstance?.vendor?.name}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
			<auth:hasOneRole roles="['REPOSITORY_ADMINISTRATOR','SERVICE_ADMINISTRATOR']">
            	<div class="buttons">
                	<g:form>
                    	<g:hiddenField name="id" value="${serviceInstance?.id}" />
                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:form>
            	</div>
			</auth:hasOneRole>
        </div>
    </body>
</html>
