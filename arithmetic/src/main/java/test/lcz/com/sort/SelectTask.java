package test.lcz.com.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019/4/16
 */
public class SelectTask {

    private long id;
    private String code;
    private int productId;
    private String sheftNo;
    private int status;
    private int operatingState;
    private String createdAt;
    private int createdBy;
    private List<PickOrderItems> pickOrderItems;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setSheftNo(String sheftNo) {
        this.sheftNo = sheftNo;
    }

    public String getSheftNo() {
        return sheftNo;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setOperatingState(int operatingState) {
        this.operatingState = operatingState;
    }

    public int getOperatingState() {
        return operatingState;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setPickOrderItems(List<PickOrderItems> pickOrderItems) {
        this.pickOrderItems = pickOrderItems;
    }

    public List<PickOrderItems> getPickOrderItems() {
        return pickOrderItems;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", productId=" + productId +
                ", sheftNo='" + sheftNo + '\'' +
                ", status=" + status +
                ", operatingState=" + operatingState +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", pickOrderItems=" + Arrays.toString(pickOrderItems.toArray()) +
                '}';
    }


    public static class PickOrderItems {

        private long id;
        private int regionId;
        private String tagAdress;
        private String partNo;
        private String colorNo;
        private int quantity;
        private String createdAt;
        private int createdBy;
        private long pickOrderId;

        public void setId(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setTagAdress(String tagAdress) {
            this.tagAdress = tagAdress;
        }

        public String getTagAdress() {
            return tagAdress;
        }

        public void setPartNo(String partNo) {
            this.partNo = partNo;
        }

        public String getPartNo() {
            return partNo;
        }

        public void setColorNo(String colorNo) {
            this.colorNo = colorNo;
        }

        public String getColorNo() {
            return colorNo;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedBy(int createdBy) {
            this.createdBy = createdBy;
        }

        public int getCreatedBy() {
            return createdBy;
        }

        public void setPickOrderId(long pickOrderId) {
            this.pickOrderId = pickOrderId;
        }

        public long getPickOrderId() {
            return pickOrderId;
        }

        @Override
        public String toString() {
            return "PickOrderItems{" +
                    "id=" + id +
                    ", regionId=" + regionId +
                    ", tagAdress=" + tagAdress +
                    ", partNo='" + partNo + '\'' +
                    ", colorNo='" + colorNo + '\'' +
                    ", quantity=" + quantity +
                    ", createdAt=" + createdAt +
                    ", createdBy=" + createdBy +
                    ", pickOrderId=" + pickOrderId +
                    '}';
        }
    }
}
