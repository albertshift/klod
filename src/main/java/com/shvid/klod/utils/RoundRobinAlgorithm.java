/*
 * Copyright (c) Alex Shvid. All Rights Reserved. 2011
 */

package com.shvid.klod.utils;

import java.util.List;

public class RoundRobinAlgorithm<T> {

  private List<T> immutableList;
  private int pos;

  public RoundRobinAlgorithm(final List<T> immutableList) {
    this.immutableList = immutableList;
    this.pos = (int) Math.round(Math.random() * immutableList.size());
  }
  
  public T next() {
    return immutableList.get(pos++ % immutableList.size());
  }
  
}
