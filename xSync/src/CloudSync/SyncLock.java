package CloudSync;

public class SyncLock {
	private int _sync_lock;
	
	public SyncLock(){
		_sync_lock = 1;
	}
	
	public void open_lock(){
		_sync_lock = 1;
	}
	public void lock_lock(){
		_sync_lock = 0;
	}
	
	public int IsLockOpen(){
		return _sync_lock;
	}
	
}
