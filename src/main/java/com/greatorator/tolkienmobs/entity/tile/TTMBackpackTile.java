package com.greatorator.tolkienmobs.entity.tile;

import com.brandon3055.brandonscore.blocks.TileBCore;
import com.brandon3055.brandonscore.inventory.ContainerSlotLayout;
import com.brandon3055.brandonscore.inventory.TileItemStackHandler;
import com.greatorator.tolkienmobs.TTMContent;
import com.greatorator.tolkienmobs.TolkienMobs;
import com.greatorator.tolkienmobs.block.BlockTTMBackpack;
import com.greatorator.tolkienmobs.block.BlockTTMSleepingBag;
import com.greatorator.tolkienmobs.client.gui.container.ContainerTTMBackpack;
import com.greatorator.tolkienmobs.handler.TTMISUtils;
import com.greatorator.tolkienmobs.handler.TTMInventoryActions;
import com.greatorator.tolkienmobs.handler.TTMSlotManager;
import com.greatorator.tolkienmobs.handler.interfaces.ITTMBackpackInventory;
import com.greatorator.tolkienmobs.item.tools.ItemTTMBackpack;
import com.greatorator.tolkienmobs.utils.TTMReference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BedPart;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TTMBackpackTile extends TileBCore implements ITTMBackpackInventory, INamedContainerProvider, INameable, ITickableTileEntity {
    public static final ContainerSlotLayout.LayoutFactory<TTMBackpackTile> SLOT_LAYOUT = (player, tile) -> new ContainerSlotLayout().playerMain(player).allTile(tile.itemHandler);
    public static int slots = 54;
    private final ItemStackHandler inventory = createHandler(TTMReference.INVENTORY_SIZE);
    private final ItemStackHandler craftingInventory = createHandler(TTMReference.CRAFTING_GRID_SIZE);
    public TileItemStackHandler itemHandler = new TileItemStackHandler(TTMReference.INVENTORY_SIZE);
    private final FluidTank waterTank = createFluidHandler(TTMReference.BASIC_TANK_CAPACITY);
    private PlayerEntity player = null;
    private boolean isSleepingBagDeployed = false;
    private int color = 0;
    private boolean ability = true;
    private int lastTime = 0;
    private ITextComponent customName = null;

    private final LazyOptional<IItemHandlerModifiable> inventoryCapability = LazyOptional.of(() -> new RangedWrapper(this.inventory, 0, 39));
    private final LazyOptional<ItemStackHandler> craftingInventoryCapability = LazyOptional.of(() -> this.craftingInventory);
    private final LazyOptional<IFluidHandler> waterFluidTankCapability = LazyOptional.of(() -> this.waterTank);
    private final TTMSlotManager slotManager = new TTMSlotManager(this);

    private final String INVENTORY = "Inventory";
    private final String CRAFTING_INVENTORY = "CraftingInventory";
    private final String WATER_TANK = "WaterTank";
    private final String SLEEPING_BAG = "SleepingBag";
    private final String ABILITY = "Ability";
    private final String LAST_TIME = "LastTime";
    private final String CUSTOM_NAME = "CustomName";

    public TTMBackpackTile()
    {
        super(TTMContent.BACKPACK_TILE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt)
    {
        super.load(state, nbt);
        this.loadAllData(nbt);
    }

    @Override
    public ItemStackHandler getInventory()
    {
        return this.inventory;
    }

    @Override
    public ItemStackHandler getCraftingGridInventory()
    {
        return this.craftingInventory;
    }

    @Override
    public FluidTank getWaterTank()
    {
        return this.waterTank;
    }

    @Override
    public void saveItems(CompoundNBT compound)
    {
        compound.put(INVENTORY, this.inventory.serializeNBT());
        compound.put(CRAFTING_INVENTORY, this.craftingInventory.serializeNBT());
    }

    @Override
    public void loadItems(CompoundNBT compound)
    {
        this.inventory.deserializeNBT(compound.getCompound(INVENTORY));
        this.craftingInventory.deserializeNBT(compound.getCompound(CRAFTING_INVENTORY));
    }

    @Override
    public void saveColor(CompoundNBT compound) {

    }

    @Override
    public void loadColor(CompoundNBT compound) {

    }

    @Override
    public void saveTank(CompoundNBT compound)
    {
        compound.put(WATER_TANK, this.waterTank.writeToNBT(new CompoundNBT()));
    }

    @Override
    public void loadTank(CompoundNBT compound)
    {
        this.waterTank.readFromNBT(compound.getCompound(WATER_TANK));
    }

    @Override
    public void saveAbility(CompoundNBT compound)
    {
        compound.putBoolean(ABILITY, this.ability);
    }

    @Override
    public void loadAbility(CompoundNBT compound)
    {
        this.ability = compound.getBoolean(ABILITY);
    }

    @Override
    public void saveTime(CompoundNBT compound)
    {
        compound.putInt(LAST_TIME, this.lastTime);
    }

    @Override
    public void loadTime(CompoundNBT compound)
    {
        this.lastTime = compound.getInt(LAST_TIME);
    }

    public void saveSleepingBag(CompoundNBT compound)
    {
        compound.putBoolean(SLEEPING_BAG, this.isSleepingBagDeployed);
    }

    public void loadSleepingBag(CompoundNBT compound)
    {
        this.isSleepingBagDeployed = compound.getBoolean(SLEEPING_BAG);
    }

    public void saveName(CompoundNBT compound)
    {
        if(this.customName != null)
        {
            compound.putString(CUSTOM_NAME, ITextComponent.Serializer.toJson(this.customName));
        }
    }

    public void loadName(CompoundNBT compound)
    {
        if(compound.contains(CUSTOM_NAME, 8))
        {
            this.customName = ITextComponent.Serializer.fromJson(compound.getString(CUSTOM_NAME));
        }
    }

    @Override
    public void saveAllData(CompoundNBT compound)
    {
        this.saveTank(compound);
        this.saveItems(compound);
        this.saveSleepingBag(compound);
        this.saveColor(compound);
        this.saveAbility(compound);
        this.saveTime(compound);
        this.saveName(compound);
        this.slotManager.saveUnsortableSlots(compound);
    }

    @Override
    public void loadAllData(CompoundNBT compound)
    {
        this.loadTank(compound);
        this.loadItems(compound);
        this.loadSleepingBag(compound);
        this.loadColor(compound);
        this.loadAbility(compound);
        this.loadTime(compound);
        this.loadName(compound);
        this.slotManager.loadUnsortableSlots(compound);
    }

    @Override
    public boolean updateTankSlots()
    {
        return TTMInventoryActions.transferContainerTank(this, getWaterTank(), TTMReference.BUCKET_IN_LEFT, this.player);
    }

    @Override
    public boolean hasColor()
    {
        return this.color != 0;
    }

    @Override
    public int getColor()
    {
        return this.color;
    }

    @Override
    public void setAbility(boolean value)
    {
        this.ability = value;
        this.setChanged();
    }

    @Override
    public int getLastTime()
    {
        return this.lastTime;
    }

    @Override
    public void setLastTime(int time)
    {
        this.lastTime = time;
    }

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }

    @Override
    public boolean isSleepingBagDeployed()
    {
        return this.isSleepingBagDeployed;
    }

    @Override
    public TTMSlotManager getSlotManager()
    {
        return slotManager;
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = TTMISUtils.getAndSplit(getInventory(), index, count);

        if(!itemstack.isEmpty())
        {
            this.setChanged();
        }
        return itemstack;
    }

    @Override
    public BlockPos getPosition()
    {
        return this.getBlockPos();
    }

    @Override
    public byte getScreenID()
    {
        return TTMReference.TILE_SCREEN_ID;
    }

    @Override
    public ItemStack getItemStack()
    {
        Block block = level.getBlockState(getBlockPos()).getBlock();

        if(block instanceof BlockTTMBackpack)
        {
            return new ItemStack(block);
        }
        return new ItemStack(TTMContent.BACKPACK.get());
    }

    @Override
    public void setUsingPlayer(@Nullable PlayerEntity player)
    {
        this.player = player;
    }

    @Override
    public void setDataChanged(byte... dataIds) {}

    @Override
    public void setChanged()
    {
        if(!level.isClientSide)
        {
            super.setChanged();
            notifyBlockUpdate();
        }
    }

    private void notifyBlockUpdate()
    {
        BlockState blockstate = getLevel().getBlockState(getBlockPos());
        level.setBlocksDirty(getBlockPos(), blockstate, blockstate);
        level.sendBlockUpdated(getBlockPos(), blockstate, blockstate, 3);
    }

    public void setSleepingBagDeployed(boolean isSleepingBagDeployed)
    {
        this.isSleepingBagDeployed = isSleepingBagDeployed;
    }

    public boolean deploySleepingBag(World world, BlockPos pos)
    {
        Direction direction = this.getBlockDirection(world.getBlockEntity(getBlockPos()));
        this.isThereSleepingBag(direction);

        if(!this.isSleepingBagDeployed)
        {
            BlockPos sleepingBagPos1 = pos.relative(direction);
            BlockPos sleepingBagPos2 = sleepingBagPos1.relative(direction);

            if(world.isEmptyBlock(sleepingBagPos2) && world.isEmptyBlock(sleepingBagPos1))
            {
                if(world.getBlockState(sleepingBagPos1.below()).isFaceSturdy(world, sleepingBagPos1.below(), Direction.UP) && world.getBlockState(sleepingBagPos2.below()).isFaceSturdy(world, sleepingBagPos2.below(), Direction.UP))
                {
                    world.playSound(null, sleepingBagPos2, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);

                    if(!world.isClientSide)
                    {
                        world.setBlockAndUpdate(sleepingBagPos1, TTMContent.SLEEPING_BAG_RED.get().defaultBlockState().setValue(BlockTTMSleepingBag.FACING, direction).setValue(BlockTTMSleepingBag.PART, BedPart.FOOT));
                        world.setBlockAndUpdate(sleepingBagPos2, TTMContent.SLEEPING_BAG_RED.get().defaultBlockState().setValue(BlockTTMSleepingBag.FACING, direction).setValue(BlockTTMSleepingBag.PART, BedPart.HEAD));

                        world.updateNeighborsAt(pos, TTMContent.SLEEPING_BAG_RED.get());
                        world.updateNeighborsAt(sleepingBagPos2, TTMContent.SLEEPING_BAG_RED.get());
                    }

                    this.isSleepingBagDeployed = true;
                    this.setChanged();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeSleepingBag(World world)
    {
        Direction blockFacing = this.getBlockDirection(world.getBlockEntity(getBlockPos()));

        this.isThereSleepingBag(blockFacing);

        if(this.isSleepingBagDeployed)
        {
            BlockPos sleepingBagPos1 = getBlockPos().relative(blockFacing);
            BlockPos sleepingBagPos2 = sleepingBagPos1.relative(blockFacing);

            if(world.getBlockState(sleepingBagPos1).getBlock() == TTMContent.SLEEPING_BAG_RED.get() && world.getBlockState(sleepingBagPos2).getBlock() == TTMContent.SLEEPING_BAG_RED.get())
            {
                world.playSound(null, sleepingBagPos2, SoundEvents.WOOL_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                world.setBlockAndUpdate(sleepingBagPos2, Blocks.AIR.defaultBlockState());
                world.setBlockAndUpdate(sleepingBagPos1, Blocks.AIR.defaultBlockState());
                this.isSleepingBagDeployed = false;
                this.setChanged();
                return true;
            }
        }
        else
        {
            this.isSleepingBagDeployed = false;
            this.setChanged();
            return true;
        }
        return false;
    }

    public boolean isThereSleepingBag(Direction direction)
    {
        if(level.getBlockState(getBlockPos().relative(direction)).getBlock() == TTMContent.SLEEPING_BAG_RED.get() && level.getBlockState(getBlockPos().relative(direction).relative(direction)).getBlock() == TTMContent.SLEEPING_BAG_RED.get())
        {
            return true;
        }
        else
        {
            this.isSleepingBagDeployed = false;
            return false;
        }
    }

    public boolean isUsableByPlayer(PlayerEntity player)
    {
        if(this.level.getBlockEntity(this.getBlockPos()) != this)
        {
            return false;
        }
        else
        {
            return player.distanceToSqr((double)this.getBlockPos().getX() + 0.5D, (double)this.getBlockPos().getY() + 0.5D, (double)this.getBlockPos().getZ() + 0.5D) <= 64.0D;
        }
    }

    public Direction getBlockDirection(TileEntity tile)
    {
        if(tile instanceof TTMBackpackTile)
        {
            if(level == null || !(level.getBlockState(getBlockPos()).getBlock() instanceof BlockTTMBackpack))
            {
                return Direction.NORTH;
            }
            return level.getBlockState(getBlockPos()).getValue(BlockTTMBackpack.FACING);
        }
        return Direction.NORTH;
    }

    public boolean drop(World world, BlockPos pos, Item item)
    {
        ItemStack stack = new ItemStack(item, 1);
        transferToItemStack(stack);
        if(this.hasCustomName())
        {
            stack.setHoverName(this.getCustomName());
        }
        ItemEntity droppedItem = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);

        return world.addFreshEntity(droppedItem);
    }

    public ItemStack transferToItemStack(ItemStack stack)
    {
        CompoundNBT compound = new CompoundNBT();
        saveTank(compound);
        saveItems(compound);
        if(this.hasColor()) this.saveColor(compound);
        saveAbility(compound);
        saveTime(compound);
        slotManager.saveUnsortableSlots(compound);
        stack.setTag(compound);
        return stack;
    }

    @Override
    public ITextComponent getName()
    {
        return this.customName != null ? this.customName : this.getDisplayName();
    }

    @Nullable
    @Override
    public ITextComponent getCustomName()
    {
        return this.customName;
    }

    public void setCustomName(ITextComponent customName)
    {
        this.customName = customName;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(this.getBlockPos(), 3, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        super.onDataPacket(net, pkt);
        this.handleUpdateTag(level.getBlockState(pkt.getPos()), pkt.getTag());
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.save(new CompoundNBT());
    }

    public void openGUI(PlayerEntity player, INamedContainerProvider containerSupplier, BlockPos pos)
    {
        if(!player.level.isClientSide && this.player == null)
        {
            NetworkHooks.openGui((ServerPlayerEntity)player, containerSupplier, pos);
        }
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inventory, PlayerEntity playerEntity)
    {
        return new ContainerTTMBackpack(id, inventory, this);
//        return new ContainerBCTile<>(TTMContent.BACKPACK_CONTAINER, id, inventory, this, SLOT_LAYOUT);
    }

    private ItemStackHandler createHandler(int size)
    {
        return new ItemStackHandler(size)
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
            {
                ResourceLocation blacklistedItems = new ResourceLocation(TolkienMobs.MODID, "blacklisted_items");

                return !(stack.getItem() instanceof ItemTTMBackpack) && !stack.getItem().is(ItemTags.getAllTags().getTag(blacklistedItems));
            }
        };
    }

    private FluidTank createFluidHandler(int capacity)
    {
        return new FluidTank(capacity)
        {
            @Override
            protected void onContentsChanged()
            {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side)
    {
        Direction direction = getBlockDirection(getTileEntity());
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            if(side == null)
            {
                return inventoryCapability.cast();
            }
            switch(side)
            {
                case DOWN:
                case UP:
                    return inventoryCapability.cast();
                case NORTH:
                case SOUTH:
                case WEST:
                case EAST:
                    if(side == direction || side == direction.getOpposite()) return craftingInventoryCapability.cast();
            }
        }
        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            if(side == null)
            {
                return waterFluidTankCapability.cast();
            }
            if(direction == Direction.NORTH)
            {
                if (side == Direction.EAST) {
                    return waterFluidTankCapability.cast();
                }
            }
            if(direction == Direction.SOUTH)
            {
                if (side == Direction.WEST) {
                    return waterFluidTankCapability.cast();
                }
            }

            if(direction == Direction.EAST)
            {
                if (side == Direction.SOUTH) {
                    return waterFluidTankCapability.cast();
                }
            }

            if(direction == Direction.WEST)
            {
                if (side == Direction.NORTH) {
                    return waterFluidTankCapability.cast();
                }
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        inventoryCapability.invalidate();
        craftingInventoryCapability.invalidate();
        waterFluidTankCapability.invalidate();
    }
}