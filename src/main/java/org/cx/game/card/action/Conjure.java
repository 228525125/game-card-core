package org.cx.game.card.action;

import java.util.HashMap;
import java.util.Map;

import org.cx.game.action.AbstractAction;
import org.cx.game.action.IAction;
import org.cx.game.card.server.Card;
import org.cx.game.card.server.Character;
import org.cx.game.card.server.Skill;
import org.cx.game.observer.Result;
import org.cx.game.tools.CommonIdentifier;

public class Conjure extends AbstractAction implements IAction {

	@Override
	public void action(Object... objects) {
		// TODO Auto-generated method stub
		Skill skill = (Skill) objects[0];
		Character target = (Character) objects[1];
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("skill", skill);
		map.put("target", target);
		String desc = "玩家 【使用技能】 "+skill.getName();
		map.put("description", desc);
		Result info = new Result(CommonIdentifier.Character_Conjure_Skill,map);
		super.notifyObservers(info);
	}
	
	@Override
	public Character getOwner() {
		// TODO Auto-generated method stub
		return (Character) super.getOwner();
	}
}
