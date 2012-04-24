
<%@ page import="srvrepo.Specification" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'specification.label', default: 'Specification')}" />
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
                            <td valign="top" class="name"><g:message code="specification.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: specificationInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="specification.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: specificationInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="specification.description.label" default="Description" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: specificationInstance, field: "description")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="specification.input.label" default="Input" /></td>
                            
                            <td valign="top" class="value"><cocktail:typeLink type="${specificationInstance.input}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="specification.output.label" default="Output" /></td>
                            
                            <td valign="top" class="value"><cocktail:typeLink type="${specificationInstance.output}" /></td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="specification.precondition.label" default="Pre-condition" /></td>
                            
                            <td valign="top" class="value"><cocktail:formulaLink formula="${specificationInstance.precondition}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="specification.postcondition.label" default="Post-condition" /></td>
                            
                            <td valign="top" class="value"><cocktail:formulaLink formula="${specificationInstance.postcondition}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="specification.services.label" default="Services" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${specificationInstance.services}" var="s">
                                    <li><cocktail:serviceLink service="${s}" /></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
			<auth:hasOneRole roles="['REPOSITORY_ADMINISTRATOR']">
            	<div class="buttons">
                	<g:form>
                    	<g:hiddenField name="id" value="${specificationInstance?.id}" />
                    	<span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    	<span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                	</g:form>
            	</div>
			</auth:hasOneRole>
        </div>
    </body>
</html>
