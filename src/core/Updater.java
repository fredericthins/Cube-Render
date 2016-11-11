package core;

public class Updater implements Runnable{

	@Override
	/**
	 * Update the program at 60 fps
	 */
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(true){
				Thread.sleep(16);
				Run.update();
			}
		}catch(InterruptedException ex){
			System.out.println("Something went wrong: update thread interrupted");
		}
	}
	
}
