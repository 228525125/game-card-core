package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Card;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class ChangeMana extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		Integer mana = (Integer) objects[0];
		getOwner().setMana(mana);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("character", getOwner());
		String desc = "玩家"+getOwner().getName()+"Mana 【改变】 ；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Character_Change_Mana,map);
		super.notifyObservers(info);
	}
	
	@Override
	public org.cx.game.card.server.Character getOwner() {
		// TODO Auto-generated method stub
		return (org.cx.game.card.server.Character) super.getOwner();
	}
}
