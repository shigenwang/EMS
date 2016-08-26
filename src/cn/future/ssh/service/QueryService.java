package cn.future.ssh.service;

import java.util.Date;
import java.util.List;

import cn.future.ssh.domain.Personnel;

public interface QueryService {
	public List<String> getConditionsSet(Personnel personnel,String loaderSign);
}
