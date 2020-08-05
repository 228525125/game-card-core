package org.cx.game.card.server.level;

import org.cx.game.arithmetic.Point;
import org.cx.game.core.GameObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 前端目前只支持单机，这里只是预留
 * @author admin
 *
 */
@Setter
@Getter
public class Place extends GameObject {

	private Point point;
	private LandformType landform;
	private Building building;
	private Treasure treasure;
	private Boolean disabled;
}
