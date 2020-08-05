package org.cx.game.card.server.level;

import org.cx.game.card.server.Player;
import org.cx.game.host.Host;
import org.cx.game.host.IPlayer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Region extends Host {
	
	public Region(String name, String playNo, String creator, Integer nop) {
		// TODO Auto-generated constructor stub
		super(name, playNo, creator, nop);
		
		IPlayer player = new Player(1, creator, this);
		playerJoinGame(player);
	}
}
