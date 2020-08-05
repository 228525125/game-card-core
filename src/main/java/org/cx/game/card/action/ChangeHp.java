package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class ChangeHp extends AbstractAction implements IAction {

	public void action(Object... objects) {
		// TODO Auto-generated method stub
		Integer hp = (Integer) objects[0];
		getOwner().setHp(hp);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("character", getOwner());
		map.put("hp", hp);
		String desc = "玩家"+getOwner().getName()+"HP 【改变】 ；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Character_Change_Hp,map);
		super.notifyObservers(info);
	}
	
	@Override
	public org.cx.game.card.server.Character getOwner() {
		// TODO Auto-generated method stub
		return (org.cx.game.card.server.Character) super.getOwner();
	}
}
