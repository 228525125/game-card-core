package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Card;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class Draw extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("card", getOwner());
		String desc = "玩家 【抽牌】 "+getOwner().getName();
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Card_Draw,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Card getOwner() {
		// TODO Auto-generated method stub
		return (Card) super.getOwner();
	}
}
