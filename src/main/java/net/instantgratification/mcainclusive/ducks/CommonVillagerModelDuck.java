// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.ducks;

public interface CommonVillagerModelDuck {
    float getRenderLeftScale();
    float getRenderRightScale();

    float getRenderLeftX();
    float getRenderLeftY();
    float getRenderLeftZ();

    float getRenderRightX();
    float getRenderRightY();
    float getRenderRightZ();

    float getRenderLeftPitch();
    float getRenderLeftYaw();
    float getRenderLeftRoll();

    float getRenderRightPitch();
    float getRenderRightYaw();
    float getRenderRightRoll();

    void setRenderLeftScale(float val);
    void setRenderRightScale(float val);

    void setRenderLeftX(float val);
    void setRenderLeftY(float val);
    void setRenderLeftZ(float val);

    void setRenderRightX(float val);
    void setRenderRightY(float val);
    void setRenderRightZ(float val);

    void setRenderLeftPitch(float val);
    void setRenderLeftYaw(float val);
    void setRenderLeftRoll(float val);

    void setRenderRightPitch(float val);
    void setRenderRightYaw(float val);
    void setRenderRightRoll(float val);
}
