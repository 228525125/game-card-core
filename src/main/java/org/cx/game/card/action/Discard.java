package org.cx.game.card.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Card;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class Discard extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		List<Card> cards = (List<Card>) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cards", cards);
		map.put("character", getOwner());
		String desc = "玩家 【弃牌】 "+cards.size()+" 张";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Character_Discard_Card,map);
		super.notifyObservers(info);
	}
}
