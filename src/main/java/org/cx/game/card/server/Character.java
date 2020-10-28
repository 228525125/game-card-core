package org.cx.game.card.server;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.card.action.ChangeHp;
import org.cx.game.card.action.ChangeMana;
import org.cx.game.card.action.ChangeShield;
import org.cx.game.card.action.Conjure;
import org.cx.game.card.action.Discard;
import org.cx.game.card.action.Draw;
import org.cx.game.card.action.Play;
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
	
	@JsonIgnore
	private Play play = null;
	
	@JsonIgnore
	private Draw draw = null;
	
	@JsonIgnore
	private Discard discard = null;
	
	@JsonIgnore
	private Conjure conjure = null;
	
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
	
	public Play getPlay() {
		if(null==play){
			play = new Play();
			play.setOwner(this);
		}
		return play;
	}
	
	public Draw getDraw() {
		if(null==draw) {
			draw = new Draw();
			draw.setOwner(this);
		}
		return draw;
	}
	
	public Discard getDiscard() {
		if(null==discard) {
			discard = new Discard();
			discard.setOwner(this);
		}
		return discard;
	}
	
	public Conjure getConjure() {
		if(null==conjure) {
			conjure = new Conjure();
			conjure.setOwner(this);
		}
		return conjure;
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
	
	public void play(Card card, Character target) {
		IAction action = new ActionProxyHelper(getPlay());
		action.action(card, target);
	}
	
	public void draw(List<Card> cards) {
		IAction action = new ActionProxyHelper(getDraw());
		action.action(cards);
	}
	
	public void discard(List<Card> cards) {
		IAction action = new ActionProxyHelper(getDiscard());
		action.action(cards);
	}
	
	public void conjure(Skill skill, Character target) {
		IAction action = new ActionProxyHelper(getConjure());
		action.action(skill, target);
	}
	
}
