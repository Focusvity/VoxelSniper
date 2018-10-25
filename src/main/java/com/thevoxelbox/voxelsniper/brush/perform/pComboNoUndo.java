/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import com.thevoxelbox.voxelsniper.CoreProtectManager;
import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * @author Voxel
 */
public class pComboNoUndo extends vPerformer
{

    private int i;
    private byte d;
    private Player player;

    public pComboNoUndo()
    {
        name = "Combo, No-Undo"; // made name more descriptive - Giltwist
    }

    @Override
    public void init(SnipeData v)
    {
        w = v.getWorld();
        i = v.getVoxelId();
        d = v.getData();
        player = v.owner().getPlayer();
    }

    @Override
    public void info(Message vm)
    {
        vm.performerName(name);
        vm.voxel();
        vm.data();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void perform(Block b)
    {
        if (b.getTypeId() != i || b.getData() != d)
        {
            if (b.getType() != Material.AIR)
            {
                CoreProtectManager.getCoreProtectAPI().logRemoval(player.getName(), b.getLocation(), b.getType(), b.getData());
            }
            b.setTypeIdAndData(i, d, true);
            CoreProtectManager.getCoreProtectAPI().logPlacement(player.getName(), b.getLocation(), Material.getMaterial(i), d);
        }
    }
}