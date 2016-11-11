package core;

public class Updater implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				Thread.sleep(16);
				Run.window.Update();;
			}
		}catch(InterruptedException ex){
			System.out.println("Something went wrong: update thread interrupted");
		}
	}
	
}
