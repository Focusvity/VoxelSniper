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
public class pMatComboNophys extends vPerformer
{

    private byte dr;
    private int i;
    private int ir;
    private Player player;

    public pMatComboNophys()
    {
        name = "Mat-Combo, No Physics";
    }

    @Override
    public void init(SnipeData v)
    {
        w = v.getWorld();
        dr = v.getReplaceData();
        i = v.getVoxelId();
        ir = v.getReplaceId();
        player = v.owner().getPlayer();
    }

    @Override
    public void info(Message vm)
    {
        vm.performerName(name);
        vm.voxel();
        vm.replace();
        vm.replaceData();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void perform(Block b)
    {
        if (b.getTypeId() == ir && b.getData() == dr)
        {
            if (b.getType() != Material.AIR)
            {
                CoreProtectManager.getCoreProtectAPI().logRemoval(player.getName(), b.getLocation(), b.getType(), b.getData());
            }
            h.put(b);
            b.setTypeId(i, false);
            CoreProtectManager.getCoreProtectAPI().logPlacement(player.getName(), b.getLocation(), Material.getMaterial(i), b.getData());
        }
    }

    @Override
    public boolean isUsingReplaceMaterial()
    {
        return true;
    }
}
