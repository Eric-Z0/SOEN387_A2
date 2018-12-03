<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:object prettyPrint="true">
	<json:object name="game">
		<json:property name="id" value="${game.getId()}"/>
		<json:property name="version" value="${game.getVersion()}"/>
		<json:array name="players" items="${game.getPlayerIDs()}"/>
		<json:property name="current" value="${game.getCurrentPlayerID()}"/>
		<json:array name="decks" items="${game.getDeckIDs()}"/>
		<json:object name="play">
			<json:object name="${game.getP1ID()}">
				<json:property name="status" value="${game.getP1Status()}"/>
				<json:property name="handsize" value="${game.getP1Handsize()}"/>
				<json:property name="decksize" value="${game.getP1Decksize()}"/>
				<json:property name="discardsize" value="${game.getP1Discardsize()}"/>
				<json:array name="bench" items="${game.getP1Bench()}" var="benchItem">
					<json:object>
						<json:property name="id" value="${benchItem.getBenchedPokemonID()}"/>
						<json:array name="e" items="${benchItem.getEnergyList()}"/>
						<json:property name="b" value="${benchItem.getBenchedBasePokemonID()}"/>
					</json:object>
				</json:array>
			</json:object>
			
			<json:object name="${game.getP2ID()}">
				<json:property name="status" value="${game.getP2Status()}"/>
				<json:property name="handsize" value="${game.getP2Handsize()}"/>
				<json:property name="decksize" value="${game.getP2Decksize()}"/>
				<json:property name="discardsize" value="${game.getP2Discardsize()}"/>
				<json:array name="bench" items="${game.getP2Bench()}" var="benchItem">
					<json:object>
						<json:property name="id" value="${benchItem.getBenchedPokemonID()}"/>
						<json:array name="e" items="${benchItem.getEnergyList()}"/>
						<json:property name="b" value="${benchItem.getBenchedBasePokemonID()}"/>
					</json:object>
				</json:array>
			</json:object>
		</json:object>
	</json:object>
</json:object>