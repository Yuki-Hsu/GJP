package cn.itcast.gjp.view;
/*
 * 视图层，用户看到和操作的界面
 * 数据传递给controller层实现
 * 成员位置，创建controller对象
 */

import java.util.List;
import java.util.Scanner;

import cn.itcast.gjp.controller.ZhangWuController;
import cn.itcast.gjp.domain.ZhangWu;

public class MainView {
	private ZhangWuController controller = new ZhangWuController();
	/*
	 * 实现界面效果
	 * 接受用户的输入
	 * 根据数据，调用不同的功能方法
	 */
	public void run() {
		//创建Scanner类对象，反复键盘输入
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("---------------管家婆家庭记账软件---------------");
			System.out.println("1.添加账务　2.编辑账务　3.删除账务　4.查询账务　5.退出系统");
			System.out.println("请输入要操作的功能序号[1-5]:");
			//接受用户的菜单选择
			int choose = sc.nextInt();
			//对选择的菜单判断，去调用不同的功能
			switch(choose) {
			case 1:
				addZhangWu();
				break;
			case 2:
				editZhangWu();
				break;
			case 3:
				deleteZhangWu();
				break;
			case 4:
				selectZhangWu();
				break;
			case 5:
				System.exit(0);
				break;
				
			}
		}
	}
	/*
	 * 定义方法，实现对账务的编辑功能
	 * 实现思想：
	 * 	接受用户输入
	 * 	数据的信息，封装成ZhangWu对象
	 * 	调用控制层的方法，传递ZhangWu对象，实现编辑
	 */
	public void editZhangWu() {
		//调用查询所有账务的功能
		selectAll();
		System.out.println("选择的是编辑功能，请输入数据");
		Scanner sc = new Scanner(System.in);
		//接受用户的数据
		System.out.print("输入ID:");
		int zwid = sc.nextInt();
		System.out.println("输入分类名称");
		String flname = sc.next();
		System.out.println("输入金额");
		double money = sc.nextDouble();
		System.out.println("输入账户");
		String zhanghu = sc.next();
		System.out.println("输入日期：格式XXXX-XX-xx");
		String createtime = sc.next();
		System.out.println("输入具体描述");
		String description = sc.next();
		//将用户输入的所有参数，封装成ZhangWu对象
		//输入的ID必须封装对象
		ZhangWu zw = new ZhangWu(zwid, flname, money, zhanghu, createtime, description);
		controller.editZahngWu(zw);
	}
	/*
	 * 定义方法 addZhangWu
	 * 添加账务的方法，用户在界面中选择菜单1的时候调用
	 * 实现思想：
	 * 	接受键盘输入，5项输入，调用controller层方法
	 */
	public void addZhangWu() {
		System.out.println("选择的添加账务功能，请输入以下内容");
		Scanner sc = new Scanner(System.in);
		System.out.println("输入分类名称");
		String flname = sc.next();
		System.out.println("输入金额");
		double money = sc.nextDouble();
		System.out.println("输入账户");
		String zhanghu = sc.next();
		System.out.println("输入日期：格式XXXX-XX-xx");
		String createtime = sc.next();
		System.out.println("输入具体描述");
		String description = sc.next();
		//将接收到的数据，调用controller层的方法，传递参数，实现数据添加
		//将用户输入的所有参数，封装成ZhangWu对象
		ZhangWu zw = new ZhangWu(0, flname, money, zhanghu, createtime, description);
		controller.addZhangWu(zw);
		System.out.println("恭喜添加账务成功");
	}
	/*
	 * 定义方法selectZhangWu()
	 * 显示查询方式   1 所有查询   2 条件查询
	 * 接受用户选择
	 */
	public void selectZhangWu() {
		System.out.println("1. 查询所有      2. 条件查询");
		Scanner sc = new Scanner(System.in);
		int selectChoose = sc.nextInt();
		//根据用户选择，调用不同的功能
		switch(selectChoose) {
		case 1:
			selectAll();
			break;
		case 2:
			select();
			break;
		}
	}
	/*
	 * 定义方法，实现查询所有账务数据
	 */
	public void selectAll() {
		//调用控制层中的方法，查询所有账务数据
		List<ZhangWu> list = controller.selectAll();
		if(list.size()!=0)
			print(list);
		else
			System.out.println("没有查询到数据");
	}
	private void print(List<ZhangWu> list) {
		//输出表头
		System.out.println("ID\t\t类别\t\t账户\t\t金额\t\t时间\t\t说明");
		//遍历集合,结果输出控制台
		for(ZhangWu zw : list){
			System.out.println(zw.getZwid()+"\t\t"+zw.getFlname()+"\t\t"+zw.getZhanghu()+"\t\t"+zw.getMoney()+"\t\t"+zw.getCreatetime()+"\t"+zw.getDescription());
		}
	}
	/*
	 * 定义方法，实现条件查询账务数据
	 * 提供用户的输入日期,开始日期结束日期
	 * 就2个日期,传递到controller层
	 * 调用controller的方法,传递2个日期参数
	 * 获取到controller查询的结果集,打印出来
	 */
	public void select() {
		System.out.println("选择条件查询,输入日期格式XXXX-XX-XX");
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入开始日期:");
		String startDate = sc.nextLine();
		System.out.print("请输入结果日期:");
		String endDate = sc.nextLine();
		//调用controller层的方法,传递日期,获取查询结果集
		List<ZhangWu> list = controller.select(startDate, endDate);
		if(list.size()!=0)
			print(list);
		else
			System.out.println("没有查询到数据");
	}
	public void deleteZhangWu() {
		//调用查询所有账务的功能
		selectAll();
		System.out.println("选择的是删除功能，输入序号：");
		int zwid = new Scanner(System.in).nextInt();
		//调用控制层方法，传入主键ID
		controller.deleteZhangWu(zwid);
		System.out.println("删除账务成功");
	}
}
