Preismodell: ${price?.PRICE_NAME} (<g:message code="service.price.${price?.PRICE_TYPE}.label" default="${price?.PRICE_TYPE}" />)<br />
Preisstaffel:
<ul>
<g:each in="${price?.PRICE_SCALES}">
	<li>${it.PRICE} für ${it.QUANTITY} Einheiten</li>
</g:each>
</ul>