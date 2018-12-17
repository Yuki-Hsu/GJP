package cn.itcast.gjp.service;
/*
 * 业务层
 * 接受上一层，控制层controller的的数据
 * 经过计算，传递给dao
 * 调用dao层中的类，类成员位置，创建Dao类的对象
 */

import java.util.List;

import cn.itcast.gjp.dao.ZhangWuDao;
import cn.itcast.gjp.domain.ZhangWu;

public class ZhangWuService {
	private ZhangWuDao dao = new ZhangWuDao();
	/*
	 * 定义方法，实现查询所有账务数据
	 * 方法由控制层调用，方法调用dao层
	 * 返回存储账务ZhangWu对象的List集合
	 */
	public List<ZhangWu> selectAll() {
		return dao.selectAll();
	}
	public List<ZhangWu> select(String startDate, String endDate) {
		return dao.select(startDate, endDate);
	}
	/*
	 * 定义方法，实现添加账务
	 * 是由控制层调用，传递ZhangWu对象
	 */
	public void addZhangWu(ZhangWu zw) {
		dao.addZhangWu(zw);
	}
	public void editZhangWu(ZhangWu zw) {
		dao.editZhangWu(zw);
	}
	public void deleteZhangWu(int zwid) {
		dao.deleteZhangWu(zwid);
	}
}
