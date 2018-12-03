<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object prettyPrint="true">
	<json:array name="games" items="${games}" var="game">
		<json:object>
			<json:property name="id" value="${game.getId()}"/>
			<json:property name="version" value="${game.getVersion()}"/>
			<json:array name="players" items="${game.getPlayerIDs()}"/>
		</json:object>
	</json:array>
</json:object>