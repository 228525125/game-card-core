package org.cx.game.card.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Status;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class ChangeStatus extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		Status status = (Status) objects[0];		
		getOwner().addStatus(status);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("character", getOwner());
		String desc = "玩家"+getOwner().getName()+"Status 【改变】 ；";
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Character_Change_Status,map);
		super.notifyObservers(info);
	}
	
	@Override
	public org.cx.game.card.server.Character getOwner() {
		// TODO Auto-generated method stub
		return (org.cx.game.card.server.Character) super.getOwner();
	}
}
