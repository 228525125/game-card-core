package org.cx.game.card.server;

import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.card.action.CharacterCreated;
import org.cx.game.card.action.DeckCreated;
import org.cx.game.card.action.GameOver;
import org.cx.game.card.action.GameStart;
import org.cx.game.card.action.SkillsCreated;
import org.cx.game.card.action.TurnEnd;
import org.cx.game.card.action.TurnStart;
import org.cx.game.core.GameObject;
import org.cx.game.host.Host;
import org.cx.game.host.IPlayer;
import org.cx.game.tools.JsonHelper;
import org.cx.game.tools.SpringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Battle extends Host {

	public final static Integer Status_DeckCreated = 5;
	public final static Integer Status_SkillsCreated = 6;
	
	private Integer firstHand = 0;
	private EnemyType enemyType = null;

	@JsonIgnore
	private GameStart gameStart = null;
	
	@JsonIgnore
	private GameOver gameOver = null;
	
	@JsonIgnore
	private TurnStart turnStart = null;
	
	@JsonIgnore
	private TurnEnd turnEnd = null;
	
	@JsonIgnore
	private CharacterCreated characterCreated = null;
	
	@JsonIgnore
	private DeckCreated deckCreated = null;
	
	@JsonIgnore
	private SkillsCreated skillsCreated = null;

	public Battle(String name, String playNo, String creator, Integer nop) {
		// TODO Auto-generated constructor stub
		super(name, playNo, creator, nop);
		
		IPlayer player = new Player(1, creator, this);
		playerJoinGame(player);
	}
	
	public TurnStart getTurnStart() {
		if(null==turnStart){
			turnStart = new TurnStart();
			turnStart.setOwner(this);
		}
		return turnStart;
	}
	
	public TurnEnd getTurnEnd() {
		if(null==turnEnd){
			turnEnd = new TurnEnd();
			turnEnd.setOwner(this);
		}
		return turnEnd;
	}
	
	public GameStart getGameStart() {
		if(null==gameStart) {
			gameStart = new GameStart();
			gameStart.setOwner(this);
		}
		return gameStart;
	}
	
	public GameOver getGameOver() {
		if(null==gameOver) {
			gameOver = new GameOver();
			gameOver.setOwner(this);
		}
		return gameOver;
	}
	
	public CharacterCreated getCharacterCreated() {
		if(null==characterCreated) {
			characterCreated = new CharacterCreated();
			characterCreated.setOwner(this);
		}
		return characterCreated;
	}
	
	
	
	public DeckCreated getDeckCreated() {
		if(null==deckCreated) {
			deckCreated = new DeckCreated();
			deckCreated.setOwner(this);
		}
		return deckCreated;
	}
	
	public SkillsCreated getSkillsCreated() {
		if(null==skillsCreated) {
			skillsCreated = new SkillsCreated();
			skillsCreated.setOwner(this);
		}
		return skillsCreated;
	}
	
	public void turnStart(Character character) {
		IAction action = new ActionProxyHelper(getTurnStart());
		action.action(character);
	}
	
	public void turnEnd(Character character) {
		IAction action = new ActionProxyHelper(getTurnEnd());
		action.action(character);
	}
	
	public void start(Integer troop) {
		if(EnemyType.Ai.equals(enemyType) || EnemyType.Computer.equals(enemyType)) {
			setStatus(Status_Start);
			IAction action = new ActionProxyHelper(getGameStart());
			action.action();
		}else {
			getTroopStatusMap().put(troop, Status_Start);
			if(isStatus(Status_Start)) {
				setStatus(Status_Start);
				IAction action = new ActionProxyHelper(getGameStart());
				action.action();
			}
		}
	}
	
	public void gameOver(Character loser) {
		IAction action = new ActionProxyHelper(getGameOver());
		action.action(loser);
	}
	
	public void characterCreated(Integer troop) {
		getTroopStatusMap().put(troop, Status_CharacterCreated);
		if(isStatus(Status_CharacterCreated)) {
			setStatus(Status_CharacterCreated);
			IAction action = new ActionProxyHelper(getCharacterCreated());
			action.action();
		}
	}
	
	public void deckCreated(Integer troop) {
		getTroopStatusMap().put(troop, Status_DeckCreated);
		if(isStatus(Status_DeckCreated)) {
			setStatus(Status_DeckCreated);
			IAction action = new ActionProxyHelper(getDeckCreated());
			action.action();
		}
	}
	
	public void skillCreated(Integer troop) {
		getTroopStatusMap().put(troop, Status_SkillsCreated);
		if(isStatus(Status_SkillsCreated)) {
			setStatus(Status_SkillsCreated);
			IAction action = new ActionProxyHelper(getSkillsCreated());
			action.action();
		}
	}
	
	public List<Card> batchCreate(String json){
		JsonHelper helper = SpringUtils.getBean("jsonHelper");
		List<Card> list = helper.parseObject(json, new TypeReference<List<Card>>() {});
		for(Card card : list) 
			getGameObjects().put(card.getPid(), card);
		
		return list;
	}

}
