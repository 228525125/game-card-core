package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.PowerType;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class ChangePower extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		PowerType powerType = (PowerType) objects[0];
		Integer power = (Integer) objects[1];
		getOwner().getPowers().put(powerType, power);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("character", getOwner());
		map.put("type", powerType);
		map.put("power", power);
		String desc = "玩家"+getOwner().getName()+"Power 【改变】 ；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Character_Change_Power,map);
		super.notifyObservers(info);
	}
	
	@Override
	public org.cx.game.card.server.Character getOwner() {
		// TODO Auto-generated method stub
		return (org.cx.game.card.server.Character) super.getOwner();
	}
}
