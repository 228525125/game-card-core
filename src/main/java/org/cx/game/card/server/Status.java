package org.cx.game.card.server;

import org.cx.game.core.GameObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status extends GameObject {

	private StatusType type;
	private Integer modifier;
}
