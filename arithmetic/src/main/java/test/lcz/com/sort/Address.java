package test.lcz.com.sort;

import com.alibaba.fastjson.JSON;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019/4/17
 */
public class Address implements java.io.Serializable {

    private String id;
    private String mapPath;
    private double X;
    private double Y;
    private double Z;
    private double highAreaSize;
    private double outCornerSize;
    private double inCornerSize;
    private double crossSize;
    private int isNeedArrow;
    private int isHorizontal;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getX() {
        return X;
    }

    public void setY(double Y) {
        this.Y = Y;
    }

    public double getY() {
        return Y;
    }

    public void setZ(double Z) {
        this.Z = Z;
    }

    public double getZ() {
        return Z;
    }

    public void setHighAreaSize(double highAreaSize) {
        this.highAreaSize = highAreaSize;
    }

    public double getHighAreaSize() {
        return highAreaSize;
    }

    public void setOutCornerSize(double outCornerSize) {
        this.outCornerSize = outCornerSize;
    }

    public double getOutCornerSize() {
        return outCornerSize;
    }

    public void setInCornerSize(double inCornerSize) {
        this.inCornerSize = inCornerSize;
    }

    public double getInCornerSize() {
        return inCornerSize;
    }

    public void setCrossSize(double crossSize) {
        this.crossSize = crossSize;
    }

    public double getCrossSize() {
        return crossSize;
    }

    public void setIsNeedArrow(int isNeedArrow) {
        this.isNeedArrow = isNeedArrow;
    }

    public int getIsNeedArrow() {
        return isNeedArrow;
    }

    public void setIsHorizontal(int isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    public int getIsHorizontal() {
        return isHorizontal;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
