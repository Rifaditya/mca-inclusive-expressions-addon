// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.instantgratification.mcainclusive.ducks;

public interface VillagerRenderStateDuck {
    float getLeftBreastScale();
    void setLeftBreastScale(float val);
    float getRightBreastScale();
    void setRightBreastScale(float val);

    float getLeftBreastX();
    void setLeftBreastX(float val);
    float getLeftBreastY();
    void setLeftBreastY(float val);
    float getLeftBreastZ();
    void setLeftBreastZ(float val);

    float getRightBreastX();
    void setRightBreastX(float val);
    float getRightBreastY();
    void setRightBreastY(float val);
    float getRightBreastZ();
    void setRightBreastZ(float val);
}
