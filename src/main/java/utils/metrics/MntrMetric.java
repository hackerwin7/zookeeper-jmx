package utils.metrics;

import utils.constants.MntrConstants;

/**
 * Created by hp on 15-2-2.
 */
public class MntrMetric {

    public String Version;
    public long avgLatency;
    public long maxLatency;
    public long minLatency;
    public long packetsReceived;
    public long packetsSent;
    public long numAliveConnections;
    public long outstandingRequests;
    public String serverState;
    public long znodeCount;
    public long watchCount;
    public long ephemeralsCount;
    public long approximateDataSize;
    public long openFileDescriptorCount;
    public long followers;
    public long syncedFollowers;
    public long pendingSyncs;

    public void setData(String key, String value) {
        if(key.equals(MntrConstants.zk_version)) {
            Version = value;
        } else if (key.equals(MntrConstants.zk_avg_latency)) {
            avgLatency = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_max_latency)) {
            maxLatency = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_min_latency)) {
            minLatency = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_packets_received)) {
            packetsReceived = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_packets_sent)) {
            packetsSent = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_num_alive_connections)) {
            numAliveConnections = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_outstanding_requests)) {
            outstandingRequests = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_server_state)) {
            serverState = value;
        } else if (key.equals(MntrConstants.zk_znode_count)) {
            znodeCount = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_watch_count)) {
            watchCount = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_ephemerals_count)) {
            ephemeralsCount = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_approximate_data_size)) {
            approximateDataSize = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_open_file_descriptor_count)) {
            openFileDescriptorCount = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_followers)) {
            followers = Long.valueOf(value);
        } else if (key.equals(MntrConstants.zk_synced_followers)) {
            syncedFollowers = Long.valueOf(value);
        } else if(key.equals(MntrConstants.zk_pending_syncs)) {
            pendingSyncs = Long.valueOf(value);
        } else {
            //nothing
        }
    }

}
