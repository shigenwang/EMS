package cn.future.ssh.dao;

import java.util.List;

import cn.future.ssh.domain.IllegalStyle;

public interface IllegalStyleDao {

	List<IllegalStyle> findAllIllegalStyle();

	IllegalStyle findIllegaStyleById(long illegalstyleId);

}
