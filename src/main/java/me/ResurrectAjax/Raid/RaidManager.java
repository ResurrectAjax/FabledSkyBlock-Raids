package me.ResurrectAjax.Raid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.ResurrectAjax.Main.Main;

public class RaidManager {
	private HashMap<UUID, RaidParty> raidParty = new HashMap<UUID, RaidParty>();
	private HashMap<UUID, RaidBar> raidBars = new HashMap<UUID, RaidBar>();
	private HashMap<UUID, List<UUID>> partyInvites = new HashMap<UUID, List<UUID>>();
	private HashMap<UUID, Location> startPositions = new HashMap<UUID, Location>();
	
	private List<UUID> raidCommandsList = new ArrayList<UUID>();
	
	private Main main;
	
	public RaidManager(Main main) {
		this.main = main;
	}
	
	public void addStartPosition(UUID uuid, Location position) {
		startPositions.put(uuid, position);
	}
	
	public HashMap<UUID, Location> getStartPositions() {
		return startPositions;
	}
	
    public RaidBar addRaidBar(UUID uuid, RaidBar bar) {
		if(!main.getRaidMethods().getIslandSpectator().isEmpty() && main.getRaidMethods().getIslandSpectator().containsKey(uuid)) {
			main.getRaidMethods().exitRaidSpectator(Bukkit.getPlayer(uuid));	
		}
    	raidBars.put(uuid, bar);
    	return bar;
    }
    
    public void addRaidParty(UUID uuid, RaidParty party) {
    	raidParty.put(uuid, party);
    }
    
    public void addRaidCommands(Player player) {
    	raidCommandsList.add(player.getUniqueId());
    }
	
    public HashMap<UUID, RaidParty> getRaidParties() {
    	return raidParty;
    }
    
    public HashMap<UUID, RaidBar> getBossBar() {
    	return raidBars;
    }
 
    public List<UUID> getCalledRaidCommands() {
    	return raidCommandsList;
    }
    
    public void setLeader(Player player, RaidParty party) {
    	raidParty.remove(party.getLeader());
    	party.setLeader(player.getUniqueId());
    	raidParty.put(player.getUniqueId(), party);
    }
    
    public RaidParty getMembersParty(UUID player) {
    	RaidParty party = null;
    	for(UUID uuid : raidParty.keySet()) {
    		for(UUID member : raidParty.get(uuid).getMembers()) {
    			if(member.equals(player)) {
    				party = raidParty.get(uuid);
    			}
    		}
    	}
    	return party;
    }
    
    public HashMap<UUID, List<UUID>> getPartyInvites() {
    	return partyInvites;
    }
    
    public List<UUID> getRaidedIslandOwners() {
    	RaidMethods raidMethods = main.getRaidMethods();
    	List<UUID> owners = new ArrayList<UUID>();
    	
    	owners.addAll(raidMethods.getRaidedIslands().values());
    	return owners;
    }
    
    public List<UUID> getLeaders() {
    	List<UUID> leaders = new ArrayList<UUID>();
    	
    	for(UUID leader : raidParty.keySet()) {
    		leaders.add(leader);
    	}
    	return leaders;
    }
    
    
    
}
