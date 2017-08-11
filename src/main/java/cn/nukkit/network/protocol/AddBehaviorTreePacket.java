package cn.nukkit.network.protocol;

public class AddBehaviorTreePacket extends DataPacket {

    public String unknown;

    @Override
    public byte pid() {
        return ProtocolInfo.ADD_BEHAVIOR_TREE_PACKET;
    }

    @Override
    public void decode() {
        this.getString(this.unknown);
    }

    @Override
    public void encode() {
        this.reset();
        this.putString(this.unknown);
    }
}
