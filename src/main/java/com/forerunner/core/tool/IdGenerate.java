package com.forerunner.core.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdGenerate {

	private final static Logger log = LoggerFactory.getLogger(IdGenerate.class);

	private final static long twepoch = 1361753741828L;
	// 工作id
	private final int workerId;
	// 工作id为4bit
	private final static int workerIdBits = 4;
	// 工作id最大值为15
	public final static int maxWorkerId = -1 ^ -1 << workerIdBits;
	// 序列号
	private int sequence = 0;
	// 序列号为10bit
	private final static int sequenceBits = 10;
	// 序列号最大值为1023
	public final static int maxSequence = -1 ^ -1 << sequenceBits;
	// 工作id左移10位
	private final static int workerIdLeftShift = sequenceBits;
	// 时间戳左移14位
	private final static int timestampLeftShift = sequenceBits + workerIdBits;
	// 记录最后一次的时间戳
	private long lastTimestamp = -1L;
	
	
	public IdGenerate(int workerId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized Long generateId() {
		long timestamp = this.timeGen();
		// 同一秒内则序列号递增
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & maxSequence;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			log.error("Clock moved backwards.  Refusing to generate id for {} milliseconds",
					this.lastTimestamp - timestamp);
			return null;
		}
		this.lastTimestamp = timestamp;
		long nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << workerIdLeftShift)
				| (this.sequence);
		log.info("IdServiceImpl generateId completed!,the id={}", nextId);
		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		final IdGenerate generate = new IdGenerate(15);
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 100; i++) {
			final int index = i;
			/*try {
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println( (int) ((Math.random() * 16)));
				}
			});
		}
		
		
	}
}
