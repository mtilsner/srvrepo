
<%@ page import="srvrepo.Basetype" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'basetype.label', default: 'Basetype')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
			<auth:hasOneRole roles="['REPOSITORY_ADMINISTRATOR']">
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
                            <td valign="top" class="name"><g:message code="basetype.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: basetypeInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="basetype.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: basetypeInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="basetype.attributes.label" default="Attributes" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
								<ul>
                                <g:each in="${basetypeInstance.attributes}" var="a">
									<li>${a.name}: <cocktail:typeLink type="${a.type}" /></li>
                                </g:each>
								</ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="basetype.subtypes.label" default="Subtypes" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${basetypeInstance.subtypes}" var="s">
                                    <li><g:link controller="basetype" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="basetype.supertype.label" default="Supertype" /></td>
                            
                            <td valign="top" class="value"><g:link controller="basetype" action="show" id="${basetypeInstance?.supertype?.id}">${basetypeInstance?.supertype?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
			<auth:hasOneRole roles="['REPOSITORY_ADMINISTRATOR']">
            	<div class="buttons">
                	<g:form>
                    	<g:hiddenField name="id" value="${basetypeInstance?.id}" />
                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:form>
            	</div>
			</auth:hasOneRole>
        </div>
    </body>
</html>
