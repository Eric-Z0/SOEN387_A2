<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object prettyPrint="true">
	<json:array name="challenges" items="${challenges}" var="challenge">
		<json:object>
			<json:property name="id" value="${challenge.getId()}"/>
			<json:property name="version" value="${challenge.getVersion()}"/>
			<json:property name="challenger" value="${challenge.getChallengerID()}"/>
			<json:property name="challengee" value="${challenge.getChallengeeID()}"/>	
			<json:property name="status" value="${challenge.getStatus()}"/>
			<json:property name="deck" value="${challenge.getChallengerDeckID()}"/>		
		</json:object>
	</json:array>
</json:object>