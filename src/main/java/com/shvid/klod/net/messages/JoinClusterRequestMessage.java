package com.shvid.klod.net.messages;

import java.io.IOException;

import com.shvid.klod.io.kos.KoPortable;
import com.shvid.klod.io.kos.KosReader;
import com.shvid.klod.io.kos.KosWriter;
import com.shvid.klod.net.MemberRole;

public class JoinClusterRequestMessage implements KoPortable {

  private String cluster;
  private MemberRole role;

  public JoinClusterRequestMessage() {
  }

  public JoinClusterRequestMessage(String cluster, MemberRole role) {
    this.cluster = cluster;
    this.role = role;
  }

  public String getCluster() {
    return cluster;
  }

  public void setCluster(String cluster) {
    this.cluster = cluster;
  }

  public MemberRole getRole() {
    return role;
  }

  public void setRole(MemberRole role) {
    this.role = role;
  }

  @Override
  public void readFrom(KosReader reader) throws IOException {
    cluster = reader.readString(1);
    role = MemberRole.valueOf(reader.readString(2));
  }

  @Override
  public void writeTo(KosWriter writer) throws IOException {
    writer.writeString(1, cluster);
    writer.writeString(2, role.name());
  }

}
