package com.greatorator.tolkienmobs.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/** Borrowed from Jabelar https://github.com/jabelar */
public class ModelTolkienMobs extends ModelBase
{
    // create an animation cycle
    // for movement based animations you need to measure distance moved
    // and perform number of cycles per block distance moved.
    protected double distanceMovedTotal = 0.0D;
    // don't make this too large or animations will be skipped
    protected static final double CYCLES_PER_BLOCK = 3.0D; 
    protected int cycleIndex = 0;
    
	// ***************************************
	// Helper functions
	// ***************************************
    
    /**
	 * Update distance moved total.
	 *
	 * @param parEntity the par entity
	 */
	protected void updateDistanceMovedTotal(Entity parEntity) 
    {
        distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, 
              parEntity.prevPosZ);
    }
    
    /**
     * Gets the distance moved total.
     *
     * @param parEntity the par entity
     * @return the distance moved total
     */
    protected double getDistanceMovedTotal(Entity parEntity) 
    {
        return (distanceMovedTotal);
    }

    /**
     * Deg to rad.
     *
     * @param degrees the degrees
     * @return the float
     */
    protected float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
    
    /**
     * Sets the rotation.
     *
     * @param model the model
     * @param rotX the rot X
     * @param rotY the rot Y
     * @param rotZ the rot Z
     */
    protected void setRotation(ModelRenderer model, float rotX, float rotY, float rotZ)
    {
        model.rotateAngleX = degToRad(rotX);
        model.rotateAngleY = degToRad(rotY);
        model.rotateAngleZ = degToRad(rotZ);        
    }

    /**
     * Spin X.
     *
     * @param model the model
     */
    // spin methods are good for testing and debug rotation points and offsets in the model
    protected void spinX(ModelRenderer model)
    {
        model.rotateAngleX += degToRad(0.5F);
    }
    
    /**
     * Spin Y.
     *
     * @param model the model
     */
    protected void spinY(ModelRenderer model)
    {
        model.rotateAngleY += degToRad(0.5F);
    }
    
    /**
     * Spin Z.
     *
     * @param model the model
     */
    protected void spinZ(ModelRenderer model)
    {
        model.rotateAngleZ += degToRad(0.5F);
    }
    
    // This is really useful for converting the source from a Techne model export
    // which will have absolute rotation points that need to be converted before
    /**
     * Convert to child of.
     *
     * @param parChild the par child
     * @param parParent the par parent
     */
    // creating the addChild() relationship
    protected void convertToChildOf(ModelRenderer parChild, ModelRenderer parParent)
    {
    	// move child rotation point to be relative to parent
    	parChild.rotationPointX -= parParent.rotationPointX;
    	parChild.rotationPointY -= parParent.rotationPointY;
    	parChild.rotationPointZ -= parParent.rotationPointZ;
    	// make rotations relative to parent
    	parChild.rotateAngleX -= parParent.rotateAngleX;
    	parChild.rotateAngleY -= parParent.rotateAngleY;
    	parChild.rotateAngleZ -= parParent.rotateAngleZ;
    	// create relationship
    	parParent.addChild(parChild);
    }
}