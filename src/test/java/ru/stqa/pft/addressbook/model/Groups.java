package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

  private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<>();
  }

  public Groups withAdded (GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups withModified (GroupData group_before, GroupData group_after) {
    Groups groups = new Groups(this);
    groups.add(group_after);
    groups.remove(group_before);
    return groups;
  }

  public Groups without (GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }
}
