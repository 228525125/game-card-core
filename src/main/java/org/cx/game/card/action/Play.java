package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Card;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class Play extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		org.cx.game.card.server.Character target = (org.cx.game.card.server.Character) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("card", getOwner());
		map.put("target", target);
		String desc = "玩家 【出牌】 "+getOwner().getName();
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Card_Play,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Card getOwner() {
		// TODO Auto-generated method stub
		return (Card) super.getOwner();
	}
}
