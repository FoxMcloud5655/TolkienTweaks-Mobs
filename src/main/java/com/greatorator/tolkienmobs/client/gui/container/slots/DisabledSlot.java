package com.greatorator.tolkienmobs.client.gui.container.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class DisabledSlot extends Slot {
    public DisabledSlot(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPickup(PlayerEntity playerIn)
    {
        return false;
    }
}
