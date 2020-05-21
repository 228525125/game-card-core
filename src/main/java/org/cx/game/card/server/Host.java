package org.cx.game.card.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.card.action.CharacterCreated;
import org.cx.game.card.action.GameOver;
import org.cx.game.card.action.GameStart;
import org.cx.game.card.action.Play;
import org.cx.game.card.action.TurnEnd;
import org.cx.game.card.action.TurnStart;
import org.cx.game.core.GameObject;
import org.cx.game.host.Camera;
import org.cx.game.host.IHost;
import org.cx.game.host.IHostManager;
import org.cx.game.host.IPlayer;
import org.cx.game.tools.JsonHelper;
import org.cx.game.tools.SpringUtils;
import org.cx.game.tools.Util;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Host extends GameObject implements IHost {

	public final static Integer Status_Prepare = 1;
	public final static Integer Status_Ready = 2;
	public final static Integer Status_Start = 3;
	public final static Integer Status_CharacterCreated = 4;
	
	private String playNo = null;
	private String name = null;
	private String creator = null;
	private Long levelId = null;
	private Integer status = Status_Prepare;
	private Integer firstHand = 0;
	private Boolean enemyIsMachine = false;
	
	@JsonIgnore
	private Camera camera = new Camera();
	
	@JsonIgnore
	private List<Player> playerList = new ArrayList<Player>();
	
	@JsonIgnore
	private Map<Integer, GameObject> gameObjects = new HashMap<Integer, GameObject>();
	
	@JsonIgnore
	private Map<Integer, Player> troopPlayerMap = new HashMap<Integer, Player>();
	
	@JsonIgnore
	private Map<String, Integer> accountTroopMap = new HashMap<String, Integer>();
	
	@JsonIgnore
	private Map<Integer, Integer> troopStatusMap = new HashMap<Integer, Integer>();

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

	public Host(String name, String playNo, String creator, Long levelId) {
		// TODO Auto-generated constructor stub
		super();
		setHost(this);
		
		this.name = name;
		this.playNo = playNo;
		this.creator = creator;
		this.levelId = levelId;
		
		playerJoinGame(creator, 1);
		setStatus(Status_Prepare);
	}
	
	/**
	 * 加入游戏主机
	 * @param account 玩家帐号
	 */
	public void playerJoinGame(String account, Integer troop) {
		Player player = new Player(troop, account, this);
		
		this.playerList.add(player);
		this.troopPlayerMap.put(troop, player);
		this.accountTroopMap.put(account, troop);
		this.troopStatusMap.put(troop, Status_Ready);
		
		if(isStatus(Status_Ready)) setStatus(Status_Ready);
	}
	
	/**
	 * 玩家退出游戏主机
	 * @param troop 游戏中的编号
	 */
	public void playerQuitGame(String account) {
		Integer troop = getTroopForAccount(account);
		IPlayer player = getPlayer(troop);
		
		this.troopPlayerMap.remove(troop);
		this.accountTroopMap.remove(account);
		this.playerList.remove(player);
	}
	
	/**
	 * 返回一个还没有被占用的阵营
	 * @return
	 */
	@JsonIgnore
	public Integer getUsableTroop() {
		// TODO Auto-generated method stub
		Integer [] array = new Integer[2];
		array = this.troopPlayerMap.keySet().toArray(array);
		if(array[1] != null) return null;
		return array[0]+1;
	}
	
	/**
	 * 修改玩家阵营
	 * @param troop 阵营编号
	 * @param player
	 */
	public void setTroopOfPlayer(String account, Integer troop) {
		Integer tp = getTroopForAccount(account);
		if(null!=tp){
			Player player =this.troopPlayerMap.get(tp);
			this.troopPlayerMap.remove(tp);
			player.setTroop(troop);
			
			this.troopPlayerMap.put(troop, player);
			this.accountTroopMap.put(account, troop);
		}
	}
	
	@Override
	public IPlayer getPlayer(Integer troop) {
		// TODO Auto-generated method stub
		return this.troopPlayerMap.get(troop);
	}

	@Override
	public Integer getTroopForAccount(String account) {
		// TODO Auto-generated method stub
		return this.accountTroopMap.get(account);
	}
	
	private Boolean isStatus(Integer status) {
		Boolean ret = true;
		
		List<Player> playerList = getPlayerList();
		for(Player player : playerList){
			if(!status.equals(this.troopStatusMap.get(player.getTroop()))){
				ret = false;
				break;
			}
		}
		
		return ret;
	}
	
	public <T> List<T> findAllByType(Class<T> cls){
		List<T> list = new ArrayList<T>();
		for(GameObject go : getGameObjects().values()) {
			if(go.getClass().equals(cls))
				list.add((T) go);
		}
		return list;
	}
	
	public GameObject create(String cls, String json) {
		try {
			Class clz = Class.forName(cls);
			JsonHelper helper = SpringUtils.getBean("jsonHelper");
			GameObject go = (GameObject) helper.parseObject(json, clz);
			getGameObjects().put(go.getPid(), go);
			return go;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		if(enemyIsMachine) {
			setStatus(Status_Start);
			IAction action = new ActionProxyHelper(getGameStart());
			action.action();
		}else {
			troopStatusMap.put(troop, Status_Start);
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
		troopStatusMap.put(troop, Status_CharacterCreated);
		if(isStatus(Status_CharacterCreated)) {
			setStatus(Status_CharacterCreated);
			IAction action = new ActionProxyHelper(getCharacterCreated());
			action.action();
		}
	}

}
