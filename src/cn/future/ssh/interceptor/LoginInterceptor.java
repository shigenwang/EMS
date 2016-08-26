package cn.future.ssh.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 未登录用户不允许访问其他页面
 * @author HZC
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor {
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的 ActionContext 实例
		ActionContext ctx = invocation.getInvocationContext();
		Map<String, Object> session = ctx.getSession();
		// 取出名为 loginUser 的 Session 属性
		Object loginUser = session.get("loginUser");
		// 如果没有登录，则返回重新登录
		if(loginUser != null) {
			return invocation.invoke();
		}
		session.put("tip", "您还没有登录，请先登录后操作！");
		// 直接返回登录界面
		return "login2";
	}

}
