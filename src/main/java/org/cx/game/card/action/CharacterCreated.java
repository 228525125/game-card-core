package org.cx.game.card.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Character;
import org.cx.game.card.server.Host;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class CharacterCreated extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		List<Character> list = getOwner().findAllByType(Character.class);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("character1", list.get(0));
		map.put("character2", list.get(1));
		String desc = "【角色创建完成】："+list.get(0).getName()+"|"+list.get(1).getName()+"；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Character_Created,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Host getOwner() {
		// TODO Auto-generated method stub
		return (Host) super.getOwner();
	}
}
