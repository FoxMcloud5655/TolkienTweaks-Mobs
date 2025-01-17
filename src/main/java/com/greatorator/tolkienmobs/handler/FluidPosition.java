package com.greatorator.tolkienmobs.handler;

import com.greatorator.tolkienmobs.handler.interfaces.IFluidPosition;

public class FluidPosition implements IFluidPosition {
   protected final double x;
   protected final double y;
   protected final double z;

   public FluidPosition(double p_i1368_1_, double p_i1368_3_, double p_i1368_5_) {
      this.x = p_i1368_1_;
      this.y = p_i1368_3_;
      this.z = p_i1368_5_;
   }

   public double x() {
      return this.x;
   }

   public double y() {
      return this.y;
   }

   public double z() {
      return this.z;
   }
}