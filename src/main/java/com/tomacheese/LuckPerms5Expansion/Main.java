package com.tomacheese.LuckPerms5Expansion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;

public class Main extends PlaceholderExpansion {
	LuckPerms luckperms = null;

	@Override
	public boolean canRegister() {
		return Bukkit.getServicesManager().isProvidedFor(LuckPerms.class);
	}

	@Override
	public boolean register() {
		if (!canRegister()) {
			return false;
		}

		this.luckperms = Bukkit.getServicesManager().getRegistration(LuckPerms.class).getProvider();
		return super.register();
	}

	@Override
	public String onPlaceholderRequest(Player player, String identifier) {
		if (player == null || this.luckperms == null) {
			return "";
		}

		if (identifier.equals("primary_group_name")) {
			String groupname = luckperms.getUserManager().getUser(player.getUniqueId()).getPrimaryGroup();
			Group group = luckperms.getGroupManager().getGroup(groupname);
			return group != null ? group.getDisplayName() : groupname;
		}
		// TODO Other than this will be implemented later...

		return "";
	}

	@Override
	public String getIdentifier() {
		return "luckperms";
	}

	@Override
	public String getPlugin() {
		return "LuckPerms";
	}

	@Override
	public String getAuthor() {
		return "Tomachi (mine_book000) [Forked from LuckPerms/LuckPermsPlaceholders/luckperms-papi-expansion created by Luck]";
	}

	@Override
	public String getVersion() {
		return "5.1";
	}
}
