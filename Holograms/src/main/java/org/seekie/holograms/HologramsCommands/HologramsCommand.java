package org.seekie.holograms.HologramsCommands;


import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.seekie.holograms.Holograms;


public class HologramsCommand implements CommandExecutor {

    private final Holograms plugin;

    public Boolean found = false;

    public HologramsCommand(Holograms plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player)) {
            sender.sendMessage("This Command only allowed for Players");
            return false;
        }



        Player player = (Player) sender;

        if (args.length > 1){


            // --> /hologram create <Hologram_Name>

            if (args[0].equalsIgnoreCase("create")){

                StringBuilder builder = new StringBuilder();

                for (int i = 1; i < args.length; i++){
                    builder.append(args[i]).append(" ");
                }

                String finalName = builder.toString();

                if (finalName.isEmpty()){
                    player.sendMessage("Please set name for the Hologram.");
                    return false;
                }

                createHologram(finalName, player);




            // --> /hologram remove <Hologram_Name>

            }else if (args[0].equalsIgnoreCase("remove")) {

                StringBuilder builder = new StringBuilder();

                for (int i = 1; i < args.length; i++){
                    builder.append(args[i]).append(" ");
                }

                String finalName = builder.toString();

                removeHologram(finalName, player);



            }

        } else if (args.length  == 0 || args.length == 1) {
            player.sendMessage(ChatColor.RED+"/holograms create|remove <Hologram_Name>");
        }
        return true;
    }

    private void createHologram( String name, Player player){

        ArmorStand armorStand = (ArmorStand) Bukkit.getWorld(player.getWorld().getUID()).spawnEntity(player.getLocation().add(0, -1, 0), EntityType.ARMOR_STAND);

        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setCustomName(Holograms.colorize(name));

        player.sendMessage(ChatColor.GREEN+"You have created Hologram Successfully!");


    }

    private void  removeHologram( String name, Player player){

        //
        for (Entity unKnownEntity : player.getWorld().getEntities()){

            if (unKnownEntity instanceof ArmorStand){


                ArmorStand armorStand = (ArmorStand) unKnownEntity;


                if (armorStand.getCustomName().equalsIgnoreCase(plugin.colorize(name))){

                    armorStand.remove();

                    found = true;

                }
            }
        }

        if (!found){
            player.sendMessage(ChatColor.RED + "Couldn't find hologram with this name.");
        } else if (found) {

            player.sendMessage(ChatColor.GREEN +"Successfully removed!");
            found = false;
        }
    }
}
