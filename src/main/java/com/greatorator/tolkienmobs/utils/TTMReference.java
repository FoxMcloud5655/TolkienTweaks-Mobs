package com.greatorator.tolkienmobs.utils;

public class TTMReference {
    //Constants
    public static final int INVENTORY_SIZE = 73;
    public static final int CRAFTING_GRID_SIZE = 10;
    public static final int COMBINED_INVENTORY_SIZE = INVENTORY_SIZE + CRAFTING_GRID_SIZE;
    public static final int BUCKET = 1000;
    public static final int BASIC_TANK_CAPACITY = BUCKET * 3;

    //Inventory Special Slots
    public static final int END_OF_INVENTORY = INVENTORY_SIZE - 7;  //47
    public static final int TOOL_UPPER = END_OF_INVENTORY + 1;      //48
    public static final int TOOL_LOWER = TOOL_UPPER + 1;			//49
    public static final int BUCKET_IN_LEFT = TOOL_LOWER + 1;		//50
    public static final int BUCKET_OUT_LEFT = BUCKET_IN_LEFT + 1;	//51


}
