package cyy_IM_protocol;
/**
 * This class is used for object abstraction of Message ID
 * @author Ce
 *
 */
public class Message_ID {
	private int Sequence_number;
	private long Time_stamp;
	private int session_id;
	public Message_ID() {
	}
    public Message_ID(int Sequence_number, long Time_stamp) {
        this.Sequence_number = Sequence_number;
        this.Time_stamp = Time_stamp;
    }

	public Message_ID(int Sequence_number, long Time_stamp, int session_id) {
		this.Sequence_number = Sequence_number;
		this.Time_stamp = Time_stamp;
		this.session_id = session_id;
	}
	public int getSession_id() {
		return session_id;
	}

	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}
	public int getSequence_number() {
		return Sequence_number;
	}

	public void setSequence_number(int sequence_number) {
		this.Sequence_number = sequence_number;
	}

	public long getTime_stamp() {
		return Time_stamp;
	}

	public void setTime_stamp(int time_stamp) {
		Time_stamp = time_stamp;
	}
	
}