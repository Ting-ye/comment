package org.dy.dao;

import org.dy.bean.Dic;

import java.util.List;

public interface DicDao {
    List<Dic> select(Dic dic);
}
