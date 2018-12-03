<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object prettyPrint="true">
	<json:array name="cards" items="${deckObj.getCards()}" var="item">
		<json:object>
			<json:property name="id" value="${item.getId()}"/>
			<json:property name="t" value="${item.getType()}"/>
			<json:property name="n" value="${item.getName()}"/>
			<json:property name="b" value="${item.getBasicName()}"/>
		</json:object>
	</json:array>
</json:object>