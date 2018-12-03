<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object prettyPrint="true">
	<json:array name="players" items="${players}" var="player">
		<json:object>
			<json:property name="id" value="${player.getId()}"/>
			<json:property name="user" value="${player.getName()}"/>
		</json:object>
	</json:array>
</json:object>