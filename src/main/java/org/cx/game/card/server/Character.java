package org.cx.game.card.server;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.card.action.ChangeHp;
import org.cx.game.card.action.ChangeMana;
import org.cx.game.card.action.ChangeShield;
import org.cx.game.core.GameObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character extends GameObject {

	private String name = null;
	private Integer hp = 0;
	private Integer mana = 0;
	private Integer shield = 0;
	private Integer bout = 0;
	
	@JsonIgnore
	private ChangeMana changeMana = null;
	
	@JsonIgnore
	private ChangeHp changeHp = null;
	
	@JsonIgnore
	private ChangeShield changeShield = null;
	
	public void addBout() {
		bout += 1;
	}
	
	public ChangeMana getChangeMana() {
		if(null==changeMana){
			changeMana = new ChangeMana();
			changeMana.setOwner(this);
		}
		return changeMana;
	}
	
	public ChangeHp getChangeHp() {
		if(null==changeHp){
			changeHp = new ChangeHp();
			changeHp.setOwner(this);
		}
		return changeHp;
	}
	
	public ChangeShield getChangeShield() {
		if(null==changeShield){
			changeShield = new ChangeShield();
			changeShield.setOwner(this);
		}
		return changeShield;
	}
	
	public void changeMana(Integer newValue) {
		IAction action = new ActionProxyHelper(getChangeMana());
		action.action(newValue);
	}
	
	public void changeHp(Integer newValue) {
		IAction action = new ActionProxyHelper(getChangeHp());
		action.action(newValue);
	}
	
	public void changeShield(Integer newValue) {
		IAction action = new ActionProxyHelper(getChangeShield());
		action.action(newValue);
	}
	
}
