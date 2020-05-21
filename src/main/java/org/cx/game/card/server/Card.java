package org.cx.game.card.server;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.card.action.Draw;
import org.cx.game.card.action.Play;
import org.cx.game.core.GameObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="play")
public class Card extends GameObject {

	private Integer id;
	private String name;
	private Integer cost;
	private String policy;
	private Integer location;
	private Integer character;
	
	@JsonIgnore
	private Play play = null;
	
	@JsonIgnore
	private Draw draw = null;
	
	public void play(Character target) {
		IAction action = new ActionProxyHelper(getPlay());
		action.action(target);
	}
	
	public void draw() {
		IAction action = new ActionProxyHelper(getDraw());
		action.action();
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
}
