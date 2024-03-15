package so;

public class Execute {
    public static void main(String[] args) {
        Process p1 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 20);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p1, 0);
        
        Process p2 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 38);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p2, 0);
        
        Process p3 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 38);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p3, 0);
        
        Process p4 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 20);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p4, 0);
        
        SystemOperation.systemCall(SystemCallType.CLOSE_PROCESS, p2, 0);
        
        SystemOperation.systemCall(SystemCallType.READ_PROCESS, p2, 0);
        
        Process p5 = SystemOperation.systemCall(SystemCallType.CREATE_PROCESS, null, 8);
        SystemOperation.systemCall(SystemCallType.WRITE_PROCESS, p5, 0);
    }
}
