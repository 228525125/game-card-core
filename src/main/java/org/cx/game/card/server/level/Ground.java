package org.cx.game.card.server.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.arithmetic.Point;
import org.cx.game.card.ground.LandformEffect;
import org.cx.game.core.GameObject;
import org.cx.game.tools.SpringUtils;


import lombok.Getter;
import lombok.Setter;

/**
 * 前端目前只支持单机，这里只是预留
 * @author admin
 *
 */
@Setter
@Getter
public class Ground extends GameObject {
	
	private Integer xBorder = 0;                                       //边界x轴长度
	private Integer yBorder = 0;                                       //边界y轴长度
	private List<Point> disables;
	
	private Map<String,Place> pointToPlace;
	
	public Ground() {
		pointToPlace = new HashMap<String,Place>();
	}
}
