package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Host;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class TurnEnd extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		org.cx.game.card.server.Character controller = (org.cx.game.card.server.Character) objects[0];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("character", controller);
		String desc = controller.getName()+"【回合结束】；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Turn_End,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Host getOwner() {
		// TODO Auto-generated method stub
		return (Host) super.getOwner();
	}
}
