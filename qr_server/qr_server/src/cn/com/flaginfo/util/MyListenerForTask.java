package cn.com.flaginfo.util;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListenerForTask implements  ServletContextListener {

	private Thread myThread;
	
	@SuppressWarnings("static-access")
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		if(null != myThread && !myThread.isInterrupted()){
			myThread.interrupted();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String root = arg0.getServletContext().getRealPath("");
		myThread = new MyThread(root);
		myThread.start();
		
	}
	
	class MyThread extends Thread{

		private String root;
		public MyThread(String root){
			this.root = root;
		}
		@Override
		public void run() {
			System.out.println("**********************磁盘清理线程启动*******************");
			while(!this.isInterrupted()){
				try {
					System.out.println("**********************磁盘清理线程启动执行*******************");
					Thread.sleep(1000 * 60 * 60 * 24);
					this.clearDirs();
					System.out.println("**********************磁盘清理线程执行完毕*******************");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void clearDirs(){
			Enumeration<Object> enums = PropertiesUtil.getInstance().getKeys();
			while(enums.hasMoreElements()){
				
				String path = (String)enums.nextElement();
				File file= new File(root + PropertiesUtil.getInstance().getValue(path));
				file.delete();
				file.mkdirs();
			}
		}
		
	}

}
