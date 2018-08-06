package com.ruixun.sincfin.common.util;

import java.util.UUID;

/**
 *	
 */
/**
 * 全局唯一性id生成器
 * 
 * (a) id构成: 42位的时间前缀 + 10位的节点标识 + 12位的sequence避免并发的数字(12位不够用时强制得到新的时间前缀)
 * 注意这里进行了小改动: snowkflake是5位的datacenter加5位的机器id; 这里变成使用10位的机器id (b)
 * 对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id\
 * 
 * 
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年6月16日 下午3:12:04 <br/>
 *
 * @author 刘利海
 * @version
 * @since JDK 1.8
 */
public class Snowflake {

	private final long workerId;
	private final long epoch = 1403854494756L; // 时间起始标记点，作为基准，一般取系统的最近时间
	private final long workerIdBits = 10L; // 机器标识位数
	private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 机器ID最大值:
																	// 1023
	private long sequence = 0L; // 0，并发控制
	private final long sequenceBits = 12L; // 毫秒内自增位

	private final long workerIdShift = this.sequenceBits; // 12
	private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22
	private final long sequenceMask = -1L ^ -1L << this.sequenceBits; // 4095,111111111111,12位
	private long lastTimestamp = -1L;

	private Snowflake(long workerId) {
		if (workerId > this.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized String nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环);
												// 对新的timestamp，sequence从0开始
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
			return UUID.randomUUID().toString().replace("-", "");
		}

		this.lastTimestamp = timestamp;
		return (timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence)
				+ "";
	}

	public synchronized String nextId(String prefix) {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环);
												// 对新的timestamp，sequence从0开始
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
			return UUID.randomUUID().toString().replace("-", "");
		}

		this.lastTimestamp = timestamp;
		return prefix + (timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift
				| this.sequence) + "";
	}

	public synchronized String nextId(String prefix, Long last) {
		long timestamp = this.timeGen();
		StringBuffer sb = new StringBuffer();
		if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环);
												// 对新的timestamp，sequence从0开始
			this.sequence = this.sequence + 1 & this.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
			}
		} else {
			this.sequence = 0;
		}

		if (timestamp < this.lastTimestamp) {
			return UUID.randomUUID().toString().replace("-", "");
		}

		this.lastTimestamp = timestamp;
		sb.append(prefix).append((timestamp - this.epoch << this.timestampLeftShift
				| this.workerId << this.workerIdShift | this.sequence));
		String sid = sb.toString();
		if (sid.length() > 32) {
			sid = sb.substring(sb.length() - 32, sb.length());
		}
		return sid;
	}

	private static Snowflake flowSnowflake = new Snowflake(1);

	public static Snowflake getFlowSnowflakeInstance() {
		return flowSnowflake;
	}

	/**
	 * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
	 */
	private long tilNextMillis(long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	/**
	 * 获得系统当前毫秒数
	 */
	private static long timeGen() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) throws Exception {
		String str = "d0f0a8c1e0aa4b4bb4dd5b561e08124a-13";
		if (str.length() > 32) {
			System.out.println(str.substring(str.length() - 32, str.length()));
		}
	}

}