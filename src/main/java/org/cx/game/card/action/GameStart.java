package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Card;
import org.cx.game.card.server.Host;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class GameStart extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		String desc = "【游戏开始】";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Game_Start,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Host getOwner() {
		// TODO Auto-generated method stub
		return (Host) super.getOwner();
	}
}
