package com.rtm.application.protocol.message.entity.api;

public class AbortedTransactions {

    private long producerId;

    private long firstOffset;


    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    public long getFirstOffset() {
        return firstOffset;
    }

    public void setFirstOffset(long firstOffset) {
        this.firstOffset = firstOffset;
    }
}
