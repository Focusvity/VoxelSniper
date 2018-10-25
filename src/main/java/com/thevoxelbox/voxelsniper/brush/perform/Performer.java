/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thevoxelbox.voxelsniper.brush.perform;

import com.thevoxelbox.voxelsniper.Message;
import com.thevoxelbox.voxelsniper.SnipeData;

/**
 * @author Voxel
 */
public interface Performer
{

    public void parse(String[] args, SnipeData v);

    public void showInfo(Message vm);
}
