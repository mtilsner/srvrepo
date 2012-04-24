

<%@ page import="srvrepo.Basetype" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'basetype.label', default: 'Basetype')}" />
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
            <g:hasErrors bean="${basetypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${basetypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${basetypeInstance?.id}" />
                <g:hiddenField name="version" value="${basetypeInstance?.version}" />
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
                                  <label for="attributes"><g:message code="basetype.attributes.label" default="Attributes" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basetypeInstance, field: 'attributes', 'errors')}">
                                    
<g:each in="${basetypeInstance?.attributes}" var="a">
    ${a?.encodeAsHTML()}: <cocktail:typeLink type="${a.type}" /> <g:link controller="basetype" action="deleteAttribute" id="${a.id}"><img style="height:12px;" src="${g.resource(dir:'images', file:'minus.png')}" /></g:link><br />
</g:each>
<br />
<g:if test="${addAttribute}">
	<g:textField name="attribute.name" />
	<cocktail:typeSelector name="attribute.type" />
	<g:actionSubmit class="save" action="addAttribute" id="${basetypeInstance?.id}" value="${message(code: 'default.button.save.label', default: 'Save')}" />
</g:if>
<g:else>
	<g:link controller="basetype" action="edit" id="${basetypeInstance?.id}" params="['addAttribute': true]">${message(code: 'default.add.label', args: [message(code: 'attribute.label', default: 'Attribute')])}</g:link>
</g:else>

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="supertype"><g:message code="basetype.supertype.label" default="Supertype" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: basetypeInstance, field: 'supertype', 'errors')}">
                                    <g:select name="supertype.id" from="${srvrepo.Basetype.list()}" optionKey="id" value="${basetypeInstance?.supertype?.id}" noSelection="[null:'-- kein Supertyp --']"  />
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
