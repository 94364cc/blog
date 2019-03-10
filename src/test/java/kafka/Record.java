package kafka;

import java.util.List;

/**
 * Created by Administrator on 2019/3/2.
 */
public class Record {
    private Integer producerId;
    private List<Integer> consumerIds;
    private String postName;

    public Record(Integer producerId, List<Integer> consumerIds, String postName) {
        this.producerId = producerId;
        this.consumerIds = consumerIds;
        this.postName = postName;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public List<Integer> getConsumerIds() {
        return consumerIds;
    }

    public void setConsumerIds(List<Integer> consumerIds) {
        this.consumerIds = consumerIds;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
