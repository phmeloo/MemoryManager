package so.memory;

public class FrameMemory {
    private int frameNumber;
    private String processId;

    public FrameMemory(int frameNumber, String processId) {
        this.frameNumber = frameNumber;
        this.processId = processId;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Override
    public String toString() {
        return "FrameMemory{" +
                "frameNumber=" + frameNumber +
                ", processId='" + processId + '\'' +
                '}';
    }

	
}