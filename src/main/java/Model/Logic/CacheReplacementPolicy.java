package Model.Logic;
public interface CacheReplacementPolicy{
	void add(String word);
	String remove(); 
}

//public class tirgul5 {//stragedy pattern
	//interface task{
		//void start();
		//void stop();
		//void pause();
	//}


	//class TaskManager(){
	//	void add(task t){
		//	t.start();
		//	t.stop();
		//	t.pause();
		//}//end of strategy pattern
	//}