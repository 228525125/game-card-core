package org.cx.game.card.server;

import java.util.List;

import org.cx.game.action.ActionProxyHelper;
import org.cx.game.action.IAction;
import org.cx.game.card.action.ChangeMana;
import org.cx.game.card.action.ChangeStatus;
import org.cx.game.core.GameObject;
import org.cx.game.tools.IListFilter;
import org.cx.game.tools.ListUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Character extends GameObject {

	private String name = null;
	private Integer hp = 0;
	private Integer mana = 0;
	
	private List<Status> statuses;
	
	@JsonIgnore
	private ChangeMana changeMana = null;
	
	@JsonIgnore
	private ChangeStatus changeStatus = null;
	
	public ChangeMana getChangeMana() {
		if(null==changeMana){
			changeMana = new ChangeMana();
			changeMana.setOwner(this);
		}
		return changeMana;
	}
	
	public ChangeStatus getChangeStatus() {
		if(null==changeStatus){
			changeStatus = new ChangeStatus();
			changeStatus.setOwner(this);
		}
		return changeStatus;
	}
	
	public void changeMana(Integer newValue) {
		IAction action = new ActionProxyHelper(getChangeMana());
		action.action(newValue);
	}
	
	public void changeStatus(Status status) {
		IAction action = new ActionProxyHelper(getChangeStatus());
		action.action(status);
	}
	
	public List<Status> findAllByStatusType(StatusType type) {
		return ListUtils.filter(statuses, new IListFilter<Status>() {

			@Override
			public Boolean content(Status t) {
				// TODO Auto-generated method stub
				return t.getType().equals(type);
			}
		});
	}
	
	public Boolean addStatus(Status status) {
		List<Status> list = findAllByStatusType(status.getType());
		if(list.isEmpty())
			return statuses.add(status);
		else {
			Status s = list.get(0);
			Integer modifier = s.getModifier() + status.getModifier();
			s.setModifier(modifier);
			return true;
		}
	}
}
