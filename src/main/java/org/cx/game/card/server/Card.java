package org.cx.game.card.server;

import java.util.List;

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
public class Card extends GameObject {

	private Integer id;
	private String name;
	private Integer cost;
	private String policy;
	private Integer location;
	private Integer character;
	private Boolean real;
	private PowerType type;
	private List<PowerType> powers;
}
