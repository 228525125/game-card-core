package org.cx.game.card.server;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.card.action.CharacterCreated;
import org.cx.game.card.action.GameOver;
import org.cx.game.card.action.GameStart;
import org.cx.game.card.action.TurnEnd;
import org.cx.game.card.action.TurnStart;
import org.cx.game.host.Host;
import org.cx.game.host.IPlayer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Battle extends Host {

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

}
