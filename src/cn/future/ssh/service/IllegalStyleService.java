package cn.future.ssh.service;

import java.util.List;

import cn.future.ssh.domain.IllegalStyle;

public interface IllegalStyleService {

	List<IllegalStyle> findAllIllegalStyle();

	IllegalStyle findIllegaStyleById(long illegalstyleId);



}
