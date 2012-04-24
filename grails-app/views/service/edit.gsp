

<%@ page import="srvrepo.Service" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'service.label', default: 'Service')}" />
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
            <g:hasErrors bean="${serviceInstance}">
            <div class="errors">
                <g:renderErrors bean="${serviceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${serviceInstance?.id}" />
                <g:hiddenField name="version" value="${serviceInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="service.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: serviceInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" size="100" value="${serviceInstance?.name}" />
                                </td>
                            </tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="name"><g:message code="service.activated.label" default="Activated" /></label>
								</td>
								<td valign="top" class="value ${hasErrors(bean: serviceInstance, field: 'activated', 'errors')}">
									<g:checkBox name="activated" value="${serviceInstance?.activated}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name">
									<label for="name"><g:message code="service.url.label" default="URL" /></label>
								</td>
								<td valign="top" class="value ${hasErrors(bean: serviceInstance, field: 'name', 'errors')}">
									<g:textField name="url" size="100" value="${serviceInstance?.url}" />
								</td>
							</tr>

							<tr class="prop">
								<td valign="top" class="name"><g:message code="service.qualities.label" default="Qualities" /></td>

								<td valign="top" style="text-align: left;" class="value">
									<ul>
									<g:each in="${serviceInstance.qualities}" var="q">
										<li><g:link action="quality" action="show" id="${q.id}">${fieldValue(bean: q.keyword, field: "name")}</g:link></li>
										</li>
									</g:each>
									</ul>
									<auth:hasOneRole roles="['REPOSITORY_ADMINISTRATOR']">
										<br />
										<g:link controller="quality" action="create" params="[service_id:serviceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'quality.label', default: 'Quality')])}</g:link>
									</auth:hasOneRole>
								</td>
							</tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="specifications"><g:message code="service.specifications.label" default="Specifications" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: serviceInstance, field: 'specifications', 'errors')}">
<ul>
<g:each in="${serviceInstance?.specifications}" var="s">
	<li><cocktail:specificationLink specification="${s}" /> <g:link controller="service" action="deleteSpecification" id="${s.id}" params="[service:serviceInstance.id]"><img style="height:12px;" src="${g.resource(dir:'images', file:'minus.png')}" /></g:link></li>
</g:each>
</ul>
<br />
<g:if test="${addSpecification}">
	<cocktail:specificationSelector name="specification" service="${serviceInstance}" />
	<g:actionSubmit class="save" action="addSpecification" id="${serviceInstance?.id}" value="${message(code: 'default.button.save.label', default: 'Save')}" />
</g:if>
<g:else>
	<g:link controller="service" action="edit" id="${serviceInstance?.id}" params="['addSpecification': true]">${message(code: 'default.add.label', args: [message(code: 'specification.label', default: 'Specification')])}</g:link>
</g:else>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="vendor"><g:message code="service.vendor.label" default="Vendor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: serviceInstance, field: 'vendor', 'errors')}">
                                    <g:select name="vendor.id" from="${srvrepo.Vendor.list()}" optionKey="id" value="${serviceInstance?.vendor?.id}" optionValue="name" />
                                </td>
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
