package org.cx.game.card.server;

import org.cx.game.core.GameObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Skill extends GameObject {

	private Integer id;
	private String name;
	private Integer cost;
	private Integer character;
	private Integer cooldown;
	private String policy;
}
