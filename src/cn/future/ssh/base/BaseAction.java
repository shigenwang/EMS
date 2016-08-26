package cn.future.ssh.base;
import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven{
	
	//======================== ModelDriven 的支持 ===========================
	protected T model;
	
	public BaseAction() {
		
		try {
			//通过反射获取model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>)pt.getActualTypeArguments()[0];
			
			//通过反射创建model的实例
			model = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//用来封装表单传过来的实例
	public T getModel() {
		return model;
	}
	//========================= Service实例 的声明 =======================================
}
