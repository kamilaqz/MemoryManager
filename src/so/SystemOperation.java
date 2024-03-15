package so;

import so.cpu.CpuManager;
import so.memory.MemoryManager;
import so.memory.Strategy;

public class SystemOperation {
	private static MemoryManager mm;
	private static CpuManager cm;

	public static Process systemCall(SystemCallType type, Process p, int sizeInMemory) {
		if (type.equals(SystemCallType.CREATE_PROCESS)) {
			if (mm == null) {
				mm = new MemoryManager(Strategy.BEST_FIT);
			}
			if (cm == null) {
				cm = new CpuManager();

			} 
			return new Process(sizeInMemory);
		} else if (type.equals(SystemCallType.WRITE_PROCESS)) {
			mm.write(p);

		} else if (type.equals(SystemCallType.CLOSE_PROCESS)) {
			mm.delete(p);

		} else if (type.equals(SystemCallType.READ_PROCESS)) {
			mm.read(p);
		}
		
		return null;
	}

}
