package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Battle;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class TurnStart extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		org.cx.game.card.server.Character controller = (org.cx.game.card.server.Character) objects[0];
		controller.addBout();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("character", controller);
		String desc = controller.getName()+"【回合开始】；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Turn_Start,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Battle getOwner() {
		// TODO Auto-generated method stub
		return (Battle) super.getOwner();
	}
}
