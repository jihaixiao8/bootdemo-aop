package json;

import java.io.Serializable;

/**
 * Created by root on 16-9-2.
 */
public class JSONEntity implements Serializable{

    private String businessId;

    private String uuid;

    public JSONEntity() {
    }

    public JSONEntity(String businessId, String uuid) {
        this.businessId = businessId;
        this.uuid = uuid;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
